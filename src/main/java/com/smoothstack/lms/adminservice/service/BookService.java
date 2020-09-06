package com.smoothstack.lms.adminservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.adminservice.dao.BookDAO;
import com.smoothstack.lms.adminservice.dao.GenreDAO;
import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.entity.Book;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.exception.ArgumentMissingException;
import com.smoothstack.lms.adminservice.exception.IllegalRelationReferenceException;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private GenreDAO genreDAO;

    public Book getBook(long id) {
        Optional<Book> book = bookDAO.findById(id);
        book.orElseThrow(() -> new IllegalRelationReferenceException("No book with id " + id));
        return book.get();
    }

    public List<Book> getBooks() {
        return bookDAO.findAll();
    }

    public Book setBook(Book book) {
        if (book.getTitle() == null) {
            throw new ArgumentMissingException("Missing 'title'");
        }
        if (book.getPublisher().getId() == null) {
            throw new ArgumentMissingException("Missing 'publisher: {id}'");
        }
        if (book.getAuthors().size() == 0) {
            throw new ArgumentMissingException("Missing 'authors: [{id}]'");
        }
        if (book.getGenres().size() == 0) {
            throw new ArgumentMissingException("Missing 'genres: [{id}]'");
        }
        for (Author author : book.getAuthors()) {
            if (author.getId() == null) {
                throw new ArgumentMissingException("Missing 'authors [{id}]");
            }
//            if (!authorDAO.existsById(author.getId())) {
//                throw new IllegalRelationReferenceException(
//                        "The author with id of " + author.getId() + " does not exist");
//            }
        }
        for (Genre genre : book.getGenres()) {
            if (genre.getId() == null) {
                throw new ArgumentMissingException("Missing 'genres [{id}]");
            }
            if (!genreDAO.existsById(genre.getId())) {
                throw new IllegalRelationReferenceException(
                        "The genre with id of " + genre.getId() + " does not exist");
            }
        }
        return bookDAO.save(book);
    }

    public void deleteBook(long id) {
        Book book = getBook(id);
        bookDAO.delete(book);
    }
}
