package sawczuk.AutoCenter.exception;

public class InvalidRequestParametersException extends RuntimeException {

    public InvalidRequestParametersException() {
    }

    public InvalidRequestParametersException(String message) {
        super(message);
    }

    public InvalidRequestParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestParametersException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
