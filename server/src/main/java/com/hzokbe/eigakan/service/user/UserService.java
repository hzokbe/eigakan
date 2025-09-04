package com.hzokbe.eigakan.service.user;

import com.hzokbe.eigakan.exception.user.*;
import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.model.user.response.UserResponse;
import com.hzokbe.eigakan.repository.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "users", key = "#id")
    public UserResponse findById(String id) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        optionalUser = repository.findByUsername(userDetails.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        var user = optionalUser.get();

        return new UserResponse(
                id,
                user.getUsername(),
                user.getEmail(),
                new HashSet<>(user.getFavoriteMovies().stream().map(
                        m -> new MovieResponse(m.getId(), m.getTitle(), m.getGenre())
                ).collect(Collectors.toSet()))
        );
    }

    @Cacheable(value = "favorite-movies", key = "#id")
    public List<MovieResponse> findAllFavoriteMovies(String id) {
        var optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        optionalUser = repository.findByUsername(userDetails.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }

        return optionalUser.get().getFavoriteMovies().stream().map(
                m -> new MovieResponse(m.getId(), m.getTitle(), m.getGenre())
        ).collect(Collectors.toList());
    }
}
