package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.Book;
import com.smoothstack.lms.adminservice.service.BookService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/admin")
public class BookController {
    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book response = bookService.setBook(book);
        ResponseEntity<Book> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> readBooks() {
        List<Book> response = bookService.getBooks();
        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> readBookById(@PathVariable long id) {
        Book response = bookService.getBook(id);
        ResponseEntity<Book> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
        Book response = bookService.setBook(book);
        ResponseEntity<Book> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        ResponseEntity<Book> responseEntity = new ResponseEntity<>((Book) null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
