package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.BookDAO;
import com.smoothstack.lms.adminservice.dao.GenreDAO;
import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.entity.Book;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.entity.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookDAO bookDAO;

    @Mock
    private GenreDAO genreDAO;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetBook() {
        // given
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book = new Book(1L, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));
        when(bookDAO.findById(1L)).thenReturn(Optional.of(book));

        // when
        Book returned = bookService.getBook(1L);

        // then
        verify(bookDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(bookDAO);
        assertEquals(book, returned);
    }

    @Test
    public void testGetBooks() {
        // given
        List<Genre> book1genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher book1publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> book1authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book1 = new Book(1L, "How JavaScript Works", book1publisher, new HashSet<>(book1authors), new HashSet<>(book1genres));

        List<Genre> book2genres = Collections.singletonList(new Genre(2L, "Nonfiction"));
        Publisher book2publisher = new Publisher(2L, "Simon & Shuster", "New York, NY", "555-5555");
        List<Author> book2authors = Collections.singletonList(new Author(2L, "Eric Raymond"));
        Book book2 = new Book(1L, "The Cathedral and the Bazaar", book2publisher, new HashSet<>(book2authors), new HashSet<>(book2genres));

        List<Book> books = Arrays.asList(book1, book2);
        when(bookDAO.findAll()).thenReturn(books);

        // when
        List<Book> returned = bookService.getBooks();

        // then
        verify(bookDAO, times(1)).findAll();
        verifyNoMoreInteractions(bookDAO);
        assertEquals(books, returned);
    }

    @Test
    public void testSetBookWithNewBook() {
        // given
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book created = new Book(null, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));
        Book persisted = new Book(1L, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));

        when(genreDAO.existsById(1L)).thenReturn(true);
        when(bookDAO.save(created)).thenReturn(persisted);

        // when
        Book returned = bookService.setBook(created);

        // then
        ArgumentCaptor<Book> bookArgument = ArgumentCaptor.forClass(Book.class);
        verify(bookDAO, times(1)).save(bookArgument.capture());
        verifyNoMoreInteractions(bookDAO);
        assertBook(created, bookArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetBookWithExistingBook() {
        // given
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book updated = new Book(1L, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));

        when(genreDAO.existsById(1L)).thenReturn(true);
        when(bookDAO.save(updated)).thenReturn(updated);

        // when
        Book returned = bookService.setBook(updated);

        // then
        ArgumentCaptor<Book> bookArgument = ArgumentCaptor.forClass(Book.class);
        verify(bookDAO, times(1)).save(bookArgument.capture());
        verifyNoMoreInteractions(bookDAO);
        assertBook(updated, bookArgument.getValue());
        assertEquals(updated, returned);
    }

    @Test
    public void testDeleteBook() {
        // given
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book deleted = new Book(1L, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));
        when(bookDAO.findById(deleted.getId())).thenReturn(Optional.of(deleted));

        // when
        bookService.deleteBook(deleted.getId());

        // then
        verify(bookDAO, times(1)).findById(deleted.getId());
        verify(bookDAO, times(1)).delete(deleted);
        verifyNoMoreInteractions(bookDAO);
    }

    private void assertBook(Book expected, Book actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthors(), actual.getAuthors());
        assertEquals(expected.getPublisher(), actual.getPublisher());
        assertEquals(expected.getGenres(), actual.getGenres());
    }
}
