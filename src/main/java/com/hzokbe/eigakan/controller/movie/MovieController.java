package com.hzokbe.eigakan.controller.movie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.eigakan.service.movie.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }
}
