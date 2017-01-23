package sport.totalizator.exception;

import sport.totalizator.entity.User;

public class UserException extends ExceptionWithErrorList {
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

    public UserException(User user){
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
