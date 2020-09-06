package com.smoothstack.lms.adminservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.BookLoan;
import com.smoothstack.lms.adminservice.service.BookLoanService;

@RestController
@RequestMapping("/lms/admin")
public class BookLoanController {

    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private BookLoanService bookLoanService;

    @PostMapping("/loan")
    public ResponseEntity<BookLoan> createBookLoan(@RequestBody BookLoan bookLoan) {
        BookLoan response = bookLoanService.setBookLoan(bookLoan);
        ResponseEntity<BookLoan> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/loan")
    public ResponseEntity<List<BookLoan>> readBookLoans() {
        List<BookLoan> response = bookLoanService.getBookLoans();
        ResponseEntity<List<BookLoan>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/loan/{id}")
    public ResponseEntity<BookLoan> readBookLoanById(@PathVariable BookLoan.BookLoanId id) {
        BookLoan response = bookLoanService.getBookLoan(id);
        ResponseEntity<BookLoan> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/loan")
    public ResponseEntity<BookLoan> updateBookLoan(@RequestBody BookLoan bookLoan) {
        BookLoan response = bookLoanService.setBookLoan(bookLoan);
        ResponseEntity<BookLoan> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/loan/{id}")
    public ResponseEntity<BookLoan> deleteBookLoan(@PathVariable BookLoan.BookLoanId id) {
        bookLoanService.deleteBookLoan(id);
        ResponseEntity<BookLoan> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
