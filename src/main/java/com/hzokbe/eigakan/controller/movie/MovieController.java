package com.hzokbe.eigakan.controller.movie;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import com.hzokbe.eigakan.model.paginated.response.PaginatedResponse;
import com.hzokbe.eigakan.service.movie.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<MovieResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id.toString()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable UUID id, @RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id.toString(), request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id.toString());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
