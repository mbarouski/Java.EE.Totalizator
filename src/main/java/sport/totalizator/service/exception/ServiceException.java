package sport.totalizator.service.exception;

import sport.totalizator.dao.exception.DAOException;

/**
 * {@link ServiceException} represents exception that can be thrown in some
 * method of Service objects.
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
