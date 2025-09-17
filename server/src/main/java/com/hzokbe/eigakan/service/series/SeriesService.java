package com.hzokbe.eigakan.service.series;

import com.hzokbe.eigakan.exception.series.AlreadyRegisteredSeriesException;
import com.hzokbe.eigakan.exception.series.InvalidSeriesSeasonsException;
import com.hzokbe.eigakan.exception.series.InvalidSeriesTitleException;
import com.hzokbe.eigakan.model.series.Series;
import com.hzokbe.eigakan.model.series.request.SeriesRequest;
import com.hzokbe.eigakan.model.series.response.SeriesResponse;
import com.hzokbe.eigakan.repository.series.SeriesRepository;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {
    private final SeriesRepository repository;

    public SeriesService(SeriesRepository repository) {
        this.repository = repository;
    }

    public SeriesResponse save(SeriesRequest request) {
        var title = request.getTitle();

        if (title == null) {
            throw new InvalidSeriesTitleException("title cannot be null");
        }

        if (title.isBlank()) {
            throw new InvalidSeriesTitleException("title cannot be blank");
        }

        title = title.trim();

        var seasons = request.getSeasons();

        if (seasons <= 0) {
            throw new InvalidSeriesSeasonsException("number series must be positive");
        }

        var episodes = request.getEpisodes();

        if (episodes <= 0) {
            throw new InvalidSeriesSeasonsException("number of episodes must be positive");
        }

        if (repository.existsByTitle(title)) {
            throw new AlreadyRegisteredSeriesException("already registered series");
        }

        var series = new Series(title, seasons, episodes);

        series = repository.save(series);

        return new SeriesResponse(series.getId(), title, seasons, episodes);
    }
}
