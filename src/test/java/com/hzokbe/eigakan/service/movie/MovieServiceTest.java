package com.hzokbe.eigakan.service.movie;

import com.hzokbe.eigakan.repository.movie.MovieRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;
}
