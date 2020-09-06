package com.smoothstack.lms.adminservice.controller;

import com.smoothstack.lms.adminservice.entity.Genre;
import com.smoothstack.lms.adminservice.service.GenreService;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class GenreControllerTest {
    @InjectMocks
    private GenreController genreController;

    @Mock
    GenreService genreService;

    @Test
    public void testCreateGenre() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(genreService.setGenre(any(Genre.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        Genre genre = new Genre(null, "Dystopia");
        ResponseEntity<Genre> result = genreController.createGenre(genre);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(genre.getName());
    }

    @Test
    public void testReadGenres()
    {
        // given
        Genre genre1 = new Genre(1L, "Mystery");
        Genre genre2 = new Genre(2L, "Thriller");
        List<Genre> genres = Arrays.asList(genre1, genre2);

        when(genreService.getGenres()).thenReturn(genres);

        // when
        ResponseEntity<List<Genre>> result = genreController.readGenres();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(genre1.getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(genre2.getName());
    }

    @Test
    public void testReadGenreById() {
        // given
        Genre genre = new Genre(1L, "Dystopia");

        when(genreService.getGenre(genre.getId())).thenReturn(genre);

        // when
        ResponseEntity<Genre> result = genreController.readGenreById(genre.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(genre.getName());
    }

    @Test
    public void testUpdateGenre() {
        // given
        Genre genre = new Genre(1L, "Dystopia");

        when(genreService.setGenre(any(Genre.class))).thenAnswer(i -> i.getArguments()[0]);

        // when
        ResponseEntity<Genre> result = genreController.updateGenre(genre.getId(), genre);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(genre.getName());
    }

    @Test
    public void testDeleteGenre() {
        // given
        Genre genre = new Genre(1L, "Dystopia");

        doNothing().when(genreService).deleteGenre(isA(Long.class));

        // when
        ResponseEntity<Genre> result = genreController.deleteGenre(genre.getId());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }
}
