package com.hzokbe.eigakan.service.series;

import com.hzokbe.eigakan.repository.series.SeriesRepository;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {
    private final SeriesRepository repository;

    public SeriesService(SeriesRepository repository) {
        this.repository = repository;
    }
}
