package com.hzokbe.eigakan.controller.movie;

import com.hzokbe.eigakan.model.movie.request.MovieRequest;
import com.hzokbe.eigakan.model.movie.response.MovieResponse;
import com.hzokbe.eigakan.model.paginated.response.PaginatedResponse;
import com.hzokbe.eigakan.service.movie.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Register a new movie",
            description = "Registers a new movie in the system using the information provided in the request body",
            method = "POST"
    )
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all registered movies",
            description = "Get all movies registered in the system",
            method = "GET"
    )
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all movies",
            description = "Returns a paginated list of all movies registered in the system. " +
                          "Supports pagination through query parameters such as `page`, `size`, and `sort`",
            method = "GET"
    )
    public ResponseEntity<PaginatedResponse<MovieResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve movie by id",
            description = "Returns a specific movie registered in the system",
            method = "GET"
    )
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
