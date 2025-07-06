package com.hzokbe.eigakan.service.movie;

import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.exception.movie.AlreadyRegisteredMovieException;
import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.model.movie.Movie;
import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import com.hzokbe.eigakan.repository.movie.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieResponse save(MovieRequest request) {
        var title = request.getTitle();

        if (title == null) {
            throw new InvalidMovieTitleException("title cannot be null");
        }

        if (title.isBlank()) {
            throw new InvalidMovieTitleException("title cannot be blank");
        }

        title = title.trim();

        if (repository.existsByTitle(title)) {
            throw new AlreadyRegisteredMovieException("movie already registered");
        }

        var movie = new Movie(title);

        repository.save(movie);

        return new MovieResponse(movie.getId(), title);
    }
}
