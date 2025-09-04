package com.hzokbe.eigakan.model.user.response;

import com.hzokbe.eigakan.model.movie.response.MovieResponse;

import java.util.HashSet;

public class UserResponse {
    private String id;

    private String username;

    private String email;

    private HashSet<MovieResponse> favoriteMovies;

    public UserResponse(String id, String username, String email, HashSet<MovieResponse> favoriteMovies) {
        this.id = id;

        this.username = username;

        this.email = email;

        this.favoriteMovies = favoriteMovies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashSet<MovieResponse> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(HashSet<MovieResponse> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }
}
