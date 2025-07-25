package com.hzokbe.eigakan.model.movie;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hzokbe.eigakan.model.genre.Genre;

@Document(collection = "movies")
public class Movie {
    @Id
    private String id;

    private String title;

    private Genre genre;

    public Movie() {
    }

    public Movie(String title, Genre genre) {
        this.id = UUID.randomUUID().toString();

        this.title = title;

        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
