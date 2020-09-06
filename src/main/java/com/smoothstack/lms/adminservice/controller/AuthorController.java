package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.service.AuthorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/admin")
public class AuthorController {

    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author response = authorService.setAuthor(author);
        ResponseEntity<Author> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> readAuthors() {
        List<Author> response = authorService.getAuthors();
        ResponseEntity<List<Author>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> readAuthorById(@PathVariable long id) {
        Author response = authorService.getAuthor(id);
        ResponseEntity<Author> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long id, @RequestBody Author author) {
        Author response = authorService.setAuthor(author);
        ResponseEntity<Author> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping(path = "/author/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        ResponseEntity<Author> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
