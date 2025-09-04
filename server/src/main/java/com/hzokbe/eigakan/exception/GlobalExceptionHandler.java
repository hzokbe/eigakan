package com.hzokbe.eigakan.exception;

import com.hzokbe.eigakan.exception.person.AlreadyRegisteredPersonException;
import com.hzokbe.eigakan.exception.person.InvalidPersonLastNameException;
import com.hzokbe.eigakan.exception.person.InvalidPersonNameException;
import com.hzokbe.eigakan.exception.person.PersonNotFoundException;
import com.hzokbe.eigakan.exception.user.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exceptionHandler(Exception exception) {
        logger.error(exception.getMessage());

        return new ExceptionResponse("internal server error");
    }

    @ExceptionHandler(InvalidMovieTitleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidMovieTitleExceptionHandler(InvalidMovieTitleException exception) {
        logger.error("unhandled exception occurred", exception);

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

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidPasswordExceptionHandler(InvalidPasswordException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse alreadyRegisteredUserExceptionHandler(AlreadyRegisteredUserException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidEmailExceptionHandler(InvalidEmailException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidTokenExceptionHandler(InvalidTokenException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(ResetPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse resetPasswordExceptionHandler(ResetPasswordException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse userNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
