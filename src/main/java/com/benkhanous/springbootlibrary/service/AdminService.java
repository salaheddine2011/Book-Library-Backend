package com.benkhanous.springbootlibrary.service;

import com.benkhanous.springbootlibrary.dao.BookRepository;
import com.benkhanous.springbootlibrary.entity.Book;
import com.benkhanous.springbootlibrary.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;
    @Autowired
    public AdminService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
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
