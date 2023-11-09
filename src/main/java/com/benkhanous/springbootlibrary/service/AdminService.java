package com.benkhanous.springbootlibrary.service;

import com.benkhanous.springbootlibrary.dao.BookRepository;
import com.benkhanous.springbootlibrary.entity.Book;
import com.benkhanous.springbootlibrary.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;
    @Autowired
    public AdminService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }


    public void increaseBookQuantity(Long bookId) throws Exception{
        Optional<Book> book=bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new Exception("Book not Found");
        }
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()+1);
        book.get().setCopies(book.get().getCopies()+1);
        bookRepository.save(book.get());
    }

    public void decreaseBookQuantity(Long bookId) throws Exception {
        Optional<Book> book=bookRepository.findById(bookId);
        if(!book.isPresent() || book.get().getCopiesAvailable()<=0 || book.get().getCopies()<=0){
            throw new Exception("book Not Found or quantity is locked");
        }
        book.get().setCopies(book.get().getCopies()-1);
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        bookRepository.save(book.get());
    }

    public void postBook(AddBookRequest addBookRequest){
        Book book=new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setCopies(addBookRequest.getCopies());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setAuthor(addBookRequest.getAuthor());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        book.setDescription(addBookRequest.getDescription());
        bookRepository.save(book);
    }
}
