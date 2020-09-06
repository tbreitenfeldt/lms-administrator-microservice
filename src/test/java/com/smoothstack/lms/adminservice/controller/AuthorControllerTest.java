package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.Author;
import com.smoothstack.lms.adminservice.service.AuthorService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {
    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    @Test
    public void testCreateAuthor() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(authorService.setAuthor(any(Author.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        Author author = new Author(null, "Lokesh Gupta");
        ResponseEntity<Author> result = authorController.createAuthor(author);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(author.getName());
    }

    @Test
    public void testReadAuthors()
    {
        // given
        Author author1 = new Author(1L, "Lokesh Gupta");
        Author author2 = new Author(2L, "Alex Gussin");
        List<Author> authors = Arrays.asList(author1, author2);

        when(authorService.getAuthors()).thenReturn(authors);

        // when
        ResponseEntity<List<Author>> result = authorController.readAuthors();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(author1.getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(author2.getName());
    }

    @Test
    public void testReadAuthorById() {
        // given
        Author author = new Author(2L, "Alex Gussin");

        when(authorService.getAuthor(author.getId())).thenReturn(author);

        // when
        ResponseEntity<Author> result = authorController.readAuthorById(author.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(author.getName());
    }

    @Test
    public void testUpdateAuthor() {
        // given
        Author author = new Author(2L, "Alex Gussin");

        when(authorService.setAuthor(any(Author.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<Author> result = authorController.updateAuthor(author.getId(), author);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(author.getName());
    }

    @Test
    public void testDeleteAuthor() {
        // given
        Author author = new Author(2L, "Alex Gussin");

        doNothing().when(authorService).deleteAuthor(isA(Long.class));

        // when
        ResponseEntity<Author> result = authorController.deleteAuthor(author.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
