package com.smoothstack.lms.adminservice.service;

import com.smoothstack.lms.adminservice.dao.GenreDAO;
import com.smoothstack.lms.adminservice.entity.Genre;
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
public class GenreServiceTest {
    @Mock
    private GenreDAO genreDAO;

    @InjectMocks
    private GenreService genreService;

    @Test
    public void testGetGenre() {
        // given
        Genre genre = new Genre(1L, "Fiction");
        when(genreDAO.findById(1L)).thenReturn(Optional.of(genre));

        // when
        Genre returned = genreService.getGenre(1L);

        // then
        verify(genreDAO, times(1)).findById(1L);
        verifyNoMoreInteractions(genreDAO);
        assertEquals(genre, returned);
    }

    @Test
    public void testGetGenres() {
        // given
        Genre genre1 = new Genre(1L, "Fiction");
        Genre genre2 = new Genre(2L, "Nonfiction");
        List<Genre> genres = Arrays.asList(genre1, genre2);
        when(genreDAO.findAll()).thenReturn(genres);

        // when
        List<Genre> returned = genreService.getGenres();

        // then
        verify(genreDAO, times(1)).findAll();
        verifyNoMoreInteractions(genreDAO);
        assertEquals(genres, returned);
    }

    @Test
    public void testSetGenreWithNewGenre() {
        // given
        Genre created = new Genre(null, "Thriller");
        Genre persisted = new Genre(1L, "Thriller");
        when(genreDAO.save(created)).thenReturn(persisted);

        // when
        Genre returned = genreService.setGenre(created);

        // then
        ArgumentCaptor<Genre> genreArgument = ArgumentCaptor.forClass(Genre.class);
        verify(genreDAO, times(1)).save(genreArgument.capture());
        verifyNoMoreInteractions(genreDAO);
        assertGenre(created, genreArgument.getValue());
        assertEquals(persisted, returned);
    }

    @Test
    public void testSetGenreWithExistingGenre() {
        // given
        Genre updated = new Genre(1L, "Space Opera");
        when(genreDAO.save(updated)).thenReturn(updated);

        // when
        Genre returned = genreService.setGenre(updated);

        // then
        ArgumentCaptor<Genre> genreArgument = ArgumentCaptor.forClass(Genre.class);
        verify(genreDAO, times(1)).save(genreArgument.capture());
        verifyNoMoreInteractions(genreDAO);
        assertGenre(updated, genreArgument.getValue());
        assertEquals(updated, returned);
    }

    @Test
    public void testDeleteGenre() {
        // given
        Genre deleted = new Genre(1L, "Mystery");
        when(genreDAO.findById(deleted.getId())).thenReturn(Optional.of(deleted));

        // when
        genreService.deleteGenre(deleted.getId());

        // then
        verify(genreDAO, times(1)).findById(deleted.getId());
        verify(genreDAO, times(1)).delete(deleted);
        verifyNoMoreInteractions(genreDAO);
    }

    private void assertGenre(Genre expected, Genre actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
