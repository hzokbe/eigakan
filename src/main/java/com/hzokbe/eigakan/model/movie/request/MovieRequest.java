package com.hzokbe.eigakan.model.movie.request;

import com.hzokbe.eigakan.model.genre.Genre;

public class MovieRequest {
    private String title;

    private Genre genre;

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
