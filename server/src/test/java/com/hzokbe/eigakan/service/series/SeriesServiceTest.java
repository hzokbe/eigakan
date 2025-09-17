package com.hzokbe.eigakan.service.series;

import com.hzokbe.eigakan.exception.series.InvalidSeriesTitleException;
import com.hzokbe.eigakan.model.series.request.SeriesRequest;
import com.hzokbe.eigakan.repository.series.SeriesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SeriesServiceTest {
    @InjectMocks
    private SeriesService service;

    @Mock
    private SeriesRepository repository;

    @Test
    public void shouldThrowInvalidSeriesTitleException_whenSaveSeriesWithNullTitle() {
        var request = new SeriesRequest(null, 0, 0);

        assertThrows(InvalidSeriesTitleException.class, () -> service.save(request));
    }
}
