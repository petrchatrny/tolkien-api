package cz.mendelu.pef.xchatrny.tolkienapi.common.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 403 - Handler for converting spring's security default 401 error to 403 error
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDeniedException() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * 404 - Handles exceptions when entity isn't found.
     *
     * @param exception caught exception
     * @return {@link ExceptionResponse} with information of errors
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ExceptionResponse<String> handleEntityNotFound(EntityNotFoundException exception) {
        return new ExceptionResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * 422 - Handles validation exceptions.
     *
     * @param exception caught exception
     * @return {@link ExceptionResponse} with information of errors
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler
    public ExceptionResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return new ExceptionResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }


    /**
     * 422 - Handles incorrect types of input exceptions.
     *
     * @param exception caught exception
     * @return {@link ExceptionResponse} with information of errors
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ExceptionResponse<String> handleIncorrectInputTypes(Exception exception) {
        return new ExceptionResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
    }
}
