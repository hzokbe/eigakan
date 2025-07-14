package com.hzokbe.eigakan.exception;

import com.hzokbe.eigakan.exception.person.AlreadyRegisteredPersonException;
import com.hzokbe.eigakan.exception.person.InvalidPersonLastNameException;
import com.hzokbe.eigakan.exception.person.InvalidPersonNameException;
import com.hzokbe.eigakan.exception.person.PersonNotFoundException;
import com.hzokbe.eigakan.exception.user.InvalidUsernameException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hzokbe.eigakan.exception.genre.InvalidGenreException;
import com.hzokbe.eigakan.exception.movie.AlreadyRegisteredMovieException;
import com.hzokbe.eigakan.exception.movie.InvalidMovieTitleException;
import com.hzokbe.eigakan.exception.movie.MovieNotFoundException;
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

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse movieNotFoundExceptionHandler(MovieNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidGenreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidGenreExceptionHandler(InvalidGenreException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidPersonNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidPersonNameExceptionHandler(InvalidPersonNameException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidPersonLastNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidPersonLastNameExceptionHandler(InvalidPersonLastNameException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredPersonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse alreadyRegisteredPersonExceptionHandler(AlreadyRegisteredPersonException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse personNotFoundExceptionHandler(PersonNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidUsernameExceptionHandler(InvalidUsernameException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
