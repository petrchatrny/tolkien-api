package cz.mendelu.pef.xchatrny.tolkienapi.shared.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This class hold information about errors that occurs.
 *
 * @param <E> type of returned error information
 */
@NoArgsConstructor
@Getter
@Setter
public final class ExceptionResponse<E> {
    private int code;
    private String message;
    private long timestamp;
    private E errors;

    /**
     * Creates new {@link ExceptionResponse}.
     *
     * @param status response status
     * @param errors errors to be returned
     */
    public ExceptionResponse(HttpStatus status, E errors) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.timestamp = System.currentTimeMillis();
        this.errors = errors;
    }
}