package com.hzokbe.eigakan.service.movie;

import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.model.genre.Genre;
import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.repository.movie.MovieRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;

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
}
