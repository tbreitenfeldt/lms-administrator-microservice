package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.entity.Book;
import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.entity.Publisher;
import com.smoothstack.lms.adminservice.service.BookService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    BookService bookService;

    @Test
    public void testCreateBook() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(bookService.setBook(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        List<Genre> genres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher publisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> authors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book = new Book(null, "How JavaScript Works", publisher, new HashSet<>(authors), new HashSet<>(genres));
        ResponseEntity<Book> result = bookController.createBook(book);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void testReadBooks()
    {
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

        when(bookService.getBooks()).thenReturn(books);

        // when
        ResponseEntity<List<Book>> result = bookController.readBooks();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getTitle()).isEqualTo(book1.getTitle());
        assertThat(result.getBody().get(0).getAuthors().contains(book1authors.get(0))).isTrue();
        assertThat(result.getBody().get(1).getTitle()).isEqualTo(book2.getTitle());
        assertThat(result.getBody().get(1).getGenres().contains(book2genres.get(0))).isTrue();
    }

    @Test
    public void testReadBookById() {
        // given
        List<Genre> bookgenres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher bookpublisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> bookauthors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book = new Book(1L, "How JavaScript Works", bookpublisher, new HashSet<>(bookauthors), new HashSet<>(bookgenres));
        when(bookService.getBook(book.getId())).thenReturn(book);

        // when
        ResponseEntity<Book> result = bookController.readBookById(book.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void testUpdateBook() {
        // given
        List<Genre> bookgenres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher bookpublisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> bookauthors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book = new Book(1L, "How JavaScript Works", bookpublisher, new HashSet<>(bookauthors), new HashSet<>(bookgenres));
        when(bookService.setBook(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<Book> result = bookController.updateBook(book.getId(), book);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // given
        List<Genre> bookgenres = Collections.singletonList(new Genre(1L, "Nonfiction"));
        Publisher bookpublisher = new Publisher(1L, "Penguin", "New York, NY", "555-5555");
        List<Author> bookauthors = Collections.singletonList(new Author(1L, "Douglas Crockford"));
        Book book = new Book(1L, "How JavaScript Works", bookpublisher, new HashSet<>(bookauthors), new HashSet<>(bookgenres));
        doNothing().when(bookService).deleteBook(isA(Long.class));

        // when
        ResponseEntity<Book> result = bookController.deleteBook(book.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
