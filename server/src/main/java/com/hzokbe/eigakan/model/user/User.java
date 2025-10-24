package com.hzokbe.eigakan.model.user;

import com.hzokbe.eigakan.model.movie.Movie;
import com.hzokbe.eigakan.model.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.UUID;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    private String passwordHash;

    private Role role = Role.USER;

    private HashSet<Movie> favoriteMovies = new HashSet<>();

    private boolean isActivated = false;

    public User() {
    }

    public User(String username, String email, String passwordHash) {
        this.id = UUID.randomUUID().toString();

        this.username = username;

        this.email = email;

        this.passwordHash = passwordHash;
    }

    public User(String username, String email, String passwordHash, Role role) {
        this.id = UUID.randomUUID().toString();

        this.username = username;

        this.email = email;

        this.passwordHash = passwordHash;

        this.role = role;
    }

    public User(String username, String email, String passwordHash, Role role, HashSet<Movie> favoriteMovies) {
        this.id = UUID.randomUUID().toString();

        this.username = username;

        this.email = email;

        this.passwordHash = passwordHash;

        this.role = role;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public HashSet<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(HashSet<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
