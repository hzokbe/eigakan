package com.hzokbe.eigakan.service.movie;

import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.repository.movie.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }
}
