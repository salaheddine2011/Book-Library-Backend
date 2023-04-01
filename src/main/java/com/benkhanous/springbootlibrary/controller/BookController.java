package com.benkhanous.springbootlibrary.controller;

import com.benkhanous.springbootlibrary.entity.Book;
import com.benkhanous.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception {
     String userEmail="anasnino@gmail.com";
     return bookService.checkoutBook(userEmail,bookId);
    }
}
