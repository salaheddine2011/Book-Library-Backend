package com.benkhanous.springbootlibrary.service;

import com.benkhanous.springbootlibrary.dao.BookRepository;
import com.benkhanous.springbootlibrary.dao.CheckoutRepository;
import com.benkhanous.springbootlibrary.entity.Book;
import com.benkhanous.springbootlibrary.entity.Checkout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;
    private CheckoutRepository checkoutRepository;

    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkoutBook(String userEmail, Long bookid) throws Exception {
        Optional<Book> book = bookRepository.findById(bookid);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookid);// for verify if the book is already checked
        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't or is already checked by the user");
        }
        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());
        Checkout checkout = new Checkout(// save to the database as a record
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );
        checkoutRepository.save(checkout);
        return book.get();
    }
    public Boolean checkoutBookByUser(String userEmail,Long bookId){
        Checkout validateCheckout=checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);
        if (validateCheckout!=null){
            return true;
        }
        else {
            return false;
        }
    }
}
