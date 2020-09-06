package com.smoothstack.lms.adminservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.adminservice.entity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {
}
