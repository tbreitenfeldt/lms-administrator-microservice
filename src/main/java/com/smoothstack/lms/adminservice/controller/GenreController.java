package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.AdministratorMicroserviceApplication;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.service.GenreService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lms/admin")
public class GenreController {

    private static final Logger logger = LogManager.getLogger(AdministratorMicroserviceApplication.class);

    @Autowired
    private GenreService genreService;

    @PostMapping("/genre")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre response = genreService.setGenre(genre);
        ResponseEntity<Genre> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> readGenres() {
        List<Genre> response = genreService.getGenres();
        ResponseEntity<List<Genre>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> readGenreById(@PathVariable long id) {
        Genre response = genreService.getGenre(id);
        ResponseEntity<Genre> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @PutMapping("/genre/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable long id, @RequestBody Genre genre) {
        Genre response = genreService.setGenre(genre);
        ResponseEntity<Genre> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }

    @DeleteMapping("/genre/{id}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable long id) {
        genreService.deleteGenre(id);
        ResponseEntity<Genre> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        logger.debug(responseEntity);
        return responseEntity;
    }
}
