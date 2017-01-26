package sport.totalizator.db.jdbc;

/**
 * This is exception can be thrown in cases connected with {@link ConnectionPool} problems.
 */
public class ConnectionPoolException extends Exception{
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
