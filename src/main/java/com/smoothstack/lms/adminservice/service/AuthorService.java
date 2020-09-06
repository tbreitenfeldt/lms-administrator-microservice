package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.AuthorDAO;
import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    public Author getAuthor(long id) {
        Optional<Author> author = authorDAO.findById(id);
        author.orElseThrow(()-> new IllegalRelationReferenceException("No author with id " + id));
        return author.get();
    }

    public List<Author> getAuthors() {
        return authorDAO.findAll();
    }

    public Author setAuthor(Author author) {
        return authorDAO.save(author);
    }

    public void deleteAuthor(long id) {
        Author author = getAuthor(id);
        authorDAO.delete(author);
    }
}
