package sport.totalizator.command.exception;

import sport.totalizator.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommandException represents exception that can be thrown in {@link ICommand}
 * interface method {@link ICommand#execute(HttpServletRequest, HttpServletResponse)}.
 */
public class CommandException extends Exception {
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
