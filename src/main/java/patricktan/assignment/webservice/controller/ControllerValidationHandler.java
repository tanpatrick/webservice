package patricktan.assignment.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author patricktan
 */
@ControllerAdvice
public class ControllerValidationHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldValidationError> handleValidationError(ConstraintViolationException ex) {
        List<FieldValidationError> errors = new ArrayList<>();

        ex.getConstraintViolations().stream().forEach((p) -> {
            errors.add(new FieldValidationError(p.getPropertyPath().toString(), p.getMessage()));
        });

        return errors;

    }

    @Data
    class FieldValidationError {

        private String field;
        private String message;

        public FieldValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
