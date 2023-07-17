package com.benkhanous.springbootlibrary.dao;

import com.benkhanous.springbootlibrary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
