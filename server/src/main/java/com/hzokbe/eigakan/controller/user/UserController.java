package com.hzokbe.eigakan.controller.user;

import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.hzokbe.eigakan.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}/my-list")
    public ResponseEntity<List<MovieResponse>> findAllFavoriteMovies(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllFavoriteMovies(id));
    }
}
