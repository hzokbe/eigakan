package com.hzokbe.eigakan.repository.series;

import com.hzokbe.eigakan.model.series.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Series, String> {
    boolean existsByTitle(String title);
}
