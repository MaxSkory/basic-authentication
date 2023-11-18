package mskory.base.authentication.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        ProblemDetail problemDetail = getProblemDetail(status, ex.getBody().getDetail());
        ex.getBindingResult().getAllErrors().forEach(e -> setError(problemDetail, e));
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler({RegistrationException.class})
    public ResponseEntity<Object> handleRegistrationException(RegistrationException ex) {
        return ResponseEntity.of(getProblemDetail(
                HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())).build();
    }

    private ProblemDetail getProblemDetail(HttpStatusCode statusCode, String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, message);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    private void setError(ProblemDetail problemDetail, ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String fieldName = ((FieldError) error).getField();
            List<String> errorMessages = addFieldErrorMessage(problemDetail, fieldError);
            problemDetail.setProperty(fieldName, errorMessages);
        } else {
            problemDetail.setProperty(error.getObjectName(), error.getDefaultMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> addFieldErrorMessage(ProblemDetail problemDetail, FieldError error) {
        List<String> errorMessages = new ArrayList<>();
        Map<String, Object> errors = problemDetail.getProperties();
        if (errors != null && errors.containsKey(error.getField())) {
            errorMessages = (List<String>) errors.get(error.getField());
            errorMessages.add(errorMessages.size() - 2, error.getDefaultMessage());
        } else {
            errorMessages.add(error.getDefaultMessage());
            Object rejectedValue = error.getRejectedValue();
            if (rejectedValue != null && rejectedValue.getClass().isArray()) {
                rejectedValue = Arrays.toString((Object[]) rejectedValue);
            }
            errorMessages.add("rejected value = " + rejectedValue);
        }
        return errorMessages;
    }
}
