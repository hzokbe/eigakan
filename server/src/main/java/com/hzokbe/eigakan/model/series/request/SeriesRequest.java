package com.hzokbe.eigakan.model.series.request;

public class SeriesRequest {
    private String title;

    private int seasons;

    private int episodes;

    public SeriesRequest() {
    }

    public SeriesRequest(String title, int seasons, int episodes) {
        this.title = title;

        this.seasons = seasons;

        this.episodes = episodes;
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
