package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.LibraryBranch;
import com.smoothstack.lms.adminservice.service.LibraryBranchService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/admin")
public class LibraryBranchController {
    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private LibraryBranchService libraryBranchService;

    @PostMapping("/branch")
    public ResponseEntity<LibraryBranch> createLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        LibraryBranch response = libraryBranchService.setLibraryBranch(libraryBranch);

        ResponseEntity<LibraryBranch> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/branch")
    public ResponseEntity<List<LibraryBranch>> readLibraryBranchs() {
        List<LibraryBranch> response = libraryBranchService.getLibraryBranches();

        ResponseEntity<List<LibraryBranch>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/branch}")
    public ResponseEntity<LibraryBranch> readLibraryBranchById(@PathVariable long id) {
        LibraryBranch response = libraryBranchService.getLibraryBranch(id);
        ResponseEntity<LibraryBranch> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/branch/{id}")
    public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable long id,
                                                             @RequestBody LibraryBranch libraryBranch) {
        LibraryBranch response = libraryBranchService.setLibraryBranch(libraryBranch);
        ResponseEntity<LibraryBranch> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable long id) {
        libraryBranchService.deleteLibraryBranch(id);
        ResponseEntity<LibraryBranch> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
