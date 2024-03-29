package sawczuk.AutoCenter.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiError {
    private Integer code;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String exception;
    private String message;
    private String debugMessage;
    private String path;
    private List<ApiSubError> subErrors;

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.code = status.value();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.code = status.value();
        this.status = status;
        this.exception = ex.getClass().getName();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, Throwable ex, String path) {
        this();
        this.code = status.value();
        this.status = status;
        this.exception = ex.getClass().getName();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
        this.path = path;
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.code = status.value();
        this.status = status;
        this.exception = ex.getClass().getName();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage().equals(message) ? null : ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable ex, String path) {
        this();
        this.code = status.value();
        this.status = status;
        this.exception = ex.getClass().getName();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage().equals(message) ? null : ex.getLocalizedMessage();
        this.path = path;
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }


    abstract class ApiSubError {

    }

    @Getter
    @Setter
    class ApiValidationError extends ApiSubError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

        ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }

        ApiValidationError(String object, String field, Object rejectedValue, String message) {
            this.object = object;
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

          //Needed?
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            ApiValidationError that = (ApiValidationError) o;
//            return Objects.equals(object, that.object) &&
//                    Objects.equals(field, that.field) &&
//                    Objects.equals(rejectedValue, that.rejectedValue) &&
//                    Objects.equals(message, that.message);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(object, field, rejectedValue, message);
//        }

    }

}
