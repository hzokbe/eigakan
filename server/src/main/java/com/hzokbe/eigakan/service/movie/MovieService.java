package com.hzokbe.eigakan.service.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.exception.genre.InvalidGenreException;
import com.hzokbe.eigakan.exception.movie.AlreadyRegisteredMovieException;
import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.exception.movie.MovieNotFoundException;
import com.hzokbe.eigakan.model.movie.Movie;
import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import com.hzokbe.eigakan.model.paginated.response.PaginatedResponse;
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

        var genre = request.getGenre();

        if (genre == null) {
            throw new InvalidGenreException("genre cannot be null");
        }

        var movie = new Movie(title, genre);

        repository.save(movie);

        return new MovieResponse(movie.getId(), title, genre);
    }

    @Cacheable(value = "movies", key = "'all'")
    public List<MovieResponse> findAll() {
        return repository.findAll().stream().map(m -> new MovieResponse(m.getId(), m.getTitle(), m.getGenre())).collect(Collectors.toList());
    }

    @Cacheable(value = "movies", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize + '_' + #pageable.sort.toString().replaceAll('[^a-zA-Z0-9]', '_').replaceAll('_+', '_')")
    public PaginatedResponse<MovieResponse> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);

        return new PaginatedResponse<>(
            page.getContent().stream().map(
                m -> new MovieResponse(
                    m.getId(),
                    m.getTitle(),
                    m.getGenre()
                )
            ).toList(),
            page.getNumber(),
            page.getTotalPages(),
            page.getSize(),
            page.getTotalElements(),
            page.isFirst(),
            page.isLast()
        );
    }

    @Cacheable(value = "movies", key = "#id")
    public MovieResponse findById(String id) {
        var optionalMovie = repository.findById(id);

        if (optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }

        var movie = optionalMovie.get();

        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getGenre());
    }

    @CachePut(value = "movies", key = "#id")
    @CacheEvict(value = "movies", key = "'all'")
    public MovieResponse update(String id, MovieRequest request) {
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

        var genre = request.getGenre();

        if (genre == null) {
            throw new InvalidGenreException("genre cannot be null");
        }

        var optionalMovie = repository.findById(id);

        if (optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }

        var movie = optionalMovie.get();

        movie.setTitle(title);

        movie.setGenre(genre);

        repository.save(movie);

        return new MovieResponse(movie.getId(), title, genre);
    }

    @Caching(evict = {
        @CacheEvict(value = "movies", key = "#id"),
        @CacheEvict(value = "movies", key = "'all'")
    })
    public void deleteById(String id) {
        var optionalMovie = repository.findById(id);

        if (optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }

        repository.deleteById(id);
    }
}
