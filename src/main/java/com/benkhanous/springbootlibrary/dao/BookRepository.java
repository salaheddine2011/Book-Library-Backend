package com.benkhanous.springbootlibrary.dao;

import com.benkhanous.springbootlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {

}
