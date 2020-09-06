package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.GenreDAO;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService {
    @Autowired
    private GenreDAO genreDAO;

    public Genre getGenre(long id) {
        Optional<Genre> genre = genreDAO.findById(id);
        genre.orElseThrow(()-> new IllegalRelationReferenceException("No author with id " + id));
        return genre.get();
    }

    public List<Genre> getGenres() {
        return genreDAO.findAll();
    }

    public Genre setGenre(Genre genre) {
        return genreDAO.save(genre);
    }

    public void deleteGenre(long id) {
        Genre genre = getGenre(id);
        genreDAO.delete(genre);
    }
}
