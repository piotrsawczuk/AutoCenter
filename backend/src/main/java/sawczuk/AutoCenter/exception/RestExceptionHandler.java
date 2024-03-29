package sawczuk.AutoCenter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        log.error(ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex, path));
    }

    @ExceptionHandler(InvalidRequestParameterException.class)
    protected ResponseEntity<Object> handleInvalidRequestParameter(
            InvalidRequestParameterException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        log.error(ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex, path));
    }

    @ExceptionHandler(PasswordException.class)
    protected ResponseEntity<Object> handlePasswordException(
            PasswordException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        log.error(ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex, path));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        log.error(ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex, path));
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, path);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<Object> handleTransactionSystemException(
            TransactionSystemException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        if (ex.getCause() != null && ex.getCause().getCause() instanceof ConstraintViolationException) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", ex.getCause().getCause(), path);
            apiError.addValidationErrors(((ConstraintViolationException) ex.getCause().getCause()).getConstraintViolations());
            return buildResponseEntity(apiError);
        } else
            return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex, path));
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex, path));
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            return buildResponseEntity(
                    new ApiError(
                            HttpStatus.CONFLICT,
                            databaseErrorBuilder(ex),
                            ex.getCause(),
                            path));
        }
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex, path));
    }

    private String databaseErrorBuilder(DataIntegrityViolationException ex) {
        String databaseError = ((org.hibernate.exception.ConstraintViolationException) ex.getCause()).getSQLException().getMessage();
        Matcher matcher = Pattern.compile("\\(([^)]+)\\)").matcher(databaseError);

        List<String> messageParts = new ArrayList<>();
        while (matcher.find()) {
            messageParts.add(matcher.group(1));
        }

        StringBuilder messageBuilder = new StringBuilder();
        if (messageParts.size() == 2) {
            messageBuilder.append(messageParts.get(0).substring(0, 1).toUpperCase()).append(messageParts.get(0).substring(1));
            messageBuilder.append(" ");
            messageBuilder.append(messageParts.get(1));
            messageBuilder.append(" already exists.");
        } else {
            messageBuilder.append("Conflict. Value already exists.");
        }
        return messageBuilder.toString();
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, path);
        apiError.setMessage(String.format(
                "The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(),
                ex.getValue(),
                ex.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex, path));
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                builder.substring(0, builder.length() - 2),
                ex,
                path));
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, path);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        log.info("{} to {}", servletWebRequest.getHttpMethod(), path);
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex, path));
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String path = servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex, path));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}


