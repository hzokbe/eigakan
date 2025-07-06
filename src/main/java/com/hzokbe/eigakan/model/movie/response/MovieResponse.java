package com.hzokbe.eigakan.model.movie.response;

import com.hzokbe.eigakan.model.genre.Genre;

public class MovieResponse {
    private String id;

    private String title;

    private Genre genre;

    public MovieResponse() {
    }

    public MovieResponse(String id, String title, Genre genre) {
        this.id = id;
        
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
