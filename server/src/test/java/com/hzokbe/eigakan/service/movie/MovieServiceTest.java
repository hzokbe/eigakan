package com.hzokbe.eigakan.service.movie;

import com.hzokbe.eigakan.exception.genre.InvalidGenreException;
import com.hzokbe.eigakan.exception.movie.AlreadyRegisteredMovieException;
import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.model.genre.Genre;
import com.hzokbe.eigakan.model.movie.Movie;
import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.repository.movie.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;

    @Test
    public void shouldSaveMovie() {
        var movie = new Movie(UUID.randomUUID().toString(), "foo", Genre.ACTION);

        when(repository.save(any(Movie.class))).thenReturn(movie);

        assertDoesNotThrow(() -> service.save(new MovieRequest(movie.getTitle(), movie.getGenre())));
    }

    @Test
    public void shouldThrowInvalidMovieTitleException_whenSavingMovieWithNullTitle() {
        var request = new MovieRequest(null, Genre.ACTION);

        assertThrows(InvalidMovieTitleException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowInvalidMovieTitleException_whenSavingMovieWithBlankTitle() {
        var request = new MovieRequest("", Genre.ACTION);

        assertThrows(InvalidMovieTitleException.class, () -> service.save(request));

        request.setTitle(" ");

        assertThrows(InvalidMovieTitleException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowAlreadyRegisteredMovieException_whenMovieAlreadyExists() {
        var request = new MovieRequest("foo", Genre.ACTION);

        when(repository.existsByTitle("foo")).thenReturn(true);

        assertThrows(AlreadyRegisteredMovieException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowInvalidGenreException_whenSavingMovieWithNullGenre() {
        var request = new MovieRequest("foo", null);

        assertThrows(InvalidGenreException.class, () -> service.save(request));
    }

    @Test
    public void shouldFindById() {
        var id = UUID.randomUUID().toString();

        var title = "foo";

        var genre = Genre.ACTION;

        when(repository.findById(id)).thenReturn(Optional.of(new Movie(id, title, genre)));

        var response = service.findById(id);

        assertEquals(title, response.getTitle());

        assertEquals(genre, response.getGenre());
    }
}
