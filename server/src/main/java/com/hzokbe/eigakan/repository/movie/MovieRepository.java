package com.hzokbe.eigakan.repository.movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hzokbe.eigakan.model.movie.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    boolean existsByTitle(String title);
}
