package peaksoft.bookapp.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.bookapp.exceptions.BookNotFoundException;
import peaksoft.bookapp.exceptions.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 500
    // 400
    // 401
    // 403
    // 404
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerBookNotFoundException(BookNotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }
    // 405
}
