package sport.totalizator.exception;

/**
 * {@link Exception} extension that we can throw in cases when user is not logged, but it's necessary.}
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
