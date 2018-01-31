package sawczuk.AutoCenter.exception;

public class ExternalApiException extends Exception {

    public ExternalApiException() {
    }

    public ExternalApiException(String message) {
        super(message);
    }

    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalApiException(Throwable cause) {
        super(cause);
    }

    public ExternalApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
