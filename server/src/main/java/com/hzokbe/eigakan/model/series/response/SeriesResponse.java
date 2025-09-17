package com.hzokbe.eigakan.model.series.response;

public class SeriesResponse {
    private String id;

    private String title;

    private int seasons;

    private int episodes;

    public SeriesResponse() {
    }

    public SeriesResponse(String id, String title, int seasons, int episodes) {
        this.id = id;

        this.title = title;

        this.seasons = seasons;

        this.episodes = episodes;
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

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
}
