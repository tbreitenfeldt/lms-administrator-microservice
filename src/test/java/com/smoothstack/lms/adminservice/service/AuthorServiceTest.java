package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.AuthorDAO;
import com.smoothstack.lms.adminservice.entity.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testGetAuthor() {
        // given
        Author author = new Author(1L, "Eric Elliot");
        when(authorDAO.findById(1L)).thenReturn(Optional.of(author));

        // when
        Author returned = authorService.getAuthor(1L);

        // then
        verify(authorDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(authorDAO);
        assertEquals(author, returned);
    }

    @Test
    public void testGetAuthors() {
        // given
        Author author1 = new Author(1L, "Eric Elliot");
        Author author2 = new Author(2L, "Eric Raymond");
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorDAO.findAll()).thenReturn(authors);

        // when
        List<Author> returned = authorService.getAuthors();

        // then
        verify(authorDAO, times(1)).findAll();
        verifyNoMoreInteractions(authorDAO);
        assertEquals(authors, returned);
    }

    @Test
    public void testSetAuthorWithNewAuthor() {
        // given
        Author created = new Author(null, "Zed Shaw");
        Author persisted = new Author(1L, "Zed Shaw");
        when(authorDAO.save(created)).thenReturn(persisted);

        // when
        Author returned = authorService.setAuthor(created);

        // then
        ArgumentCaptor<Author> authorArgument = ArgumentCaptor.forClass(Author.class);
        verify(authorDAO, times(1)).save(authorArgument.capture());
        verifyNoMoreInteractions(authorDAO);
        assertAuthor(created, authorArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetAuthorWithExistingAuthor() {
        // given
        Author updated = new Author(1L, "Xed Chaw");
        when(authorDAO.save(updated)).thenReturn(updated);

        // when
        Author returned = authorService.setAuthor(updated);

        // then
        ArgumentCaptor<Author> authorArgument = ArgumentCaptor.forClass(Author.class);
        verify(authorDAO, times(1)).save(authorArgument.capture());
        verifyNoMoreInteractions(authorDAO);
        assertAuthor(updated, authorArgument.getValue());
        assertEquals(updated, returned);
    }

    @Test
    public void testDeleteAuthor() {
        // given
        Author deleted = new Author(1L, "Zed Shaw");
        when(authorDAO.findById(deleted.getId())).thenReturn(Optional.of(deleted));

        // when
        authorService.deleteAuthor(deleted.getId());

        // then
        verify(authorDAO, times(1)).findById(deleted.getId());
        verify(authorDAO, times(1)).delete(deleted);
        verifyNoMoreInteractions(authorDAO);
    }

    private void assertAuthor(Author expected, Author actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
