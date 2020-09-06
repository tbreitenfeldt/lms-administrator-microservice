package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.Publisher;
import com.smoothstack.lms.adminservice.service.PublisherService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/admin")
public class PublisherController {

    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private PublisherService publisherService;

    @PostMapping("/publisher")
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher response = publisherService.setPublisher(publisher);
        ResponseEntity<Publisher> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/publisher")
    public ResponseEntity<List<Publisher>> readPublishers() {
        List<Publisher> response = publisherService.getPublishers();
        ResponseEntity<List<Publisher>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<Publisher> readPublisherById(@PathVariable long id) {
        Publisher response = publisherService.getPublisher(id);
        ResponseEntity<Publisher> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/publisher/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable long id, @RequestBody Publisher publisher) {
        Publisher response = publisherService.setPublisher(publisher);
        ResponseEntity<Publisher> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping(path = "/publisher/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable long id) {
        publisherService.deletePublisher(id);
        ResponseEntity<Publisher> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
