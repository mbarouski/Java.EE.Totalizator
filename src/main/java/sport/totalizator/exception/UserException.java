package sport.totalizator.exception;

import sport.totalizator.entity.User;

public class UserException extends Exception {
    private User user;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, User user){
        this(message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
