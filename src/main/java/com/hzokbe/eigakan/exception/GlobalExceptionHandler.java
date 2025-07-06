package com.hzokbe.eigakan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hzokbe.eigakan.exception.movie.AlreadyRegisteredMovieException;
import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.model.exception.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exceptionHandler(Exception exception) {
        return new ExceptionResponse("internal server error");
    }

    @ExceptionHandler(InvalidMovieTitleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidMovieTitleExceptionHandler(InvalidMovieTitleException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredMovieException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse alreadyRegisteredMovieExceptionHandler(AlreadyRegisteredMovieException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
