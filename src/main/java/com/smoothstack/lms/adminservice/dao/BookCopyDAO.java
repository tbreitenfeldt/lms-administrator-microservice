package com.smoothstack.lms.adminservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.adminservice.entity.BookCopy;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, BookCopy> {
}
