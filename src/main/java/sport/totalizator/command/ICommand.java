package sport.totalizator.command;

import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main interface of Command pattern which used to delegate
 * some actions from controller to some amount of classes.
 */
public interface ICommand {
    /**
     * Method that check need level of permissions using info about role in session.
     * @param req Instance of HttpServletRequest
     * @param needLevels Array with role enum values
     * @throws ServletException
     * @throws IOException
     * @throws CommandException
     * @throws UnauthorizedException
     */
    default void checkPermissions(HttpServletRequest req, User.Role[] needLevels)
            throws ServletException, IOException, CommandException, UnauthorizedException{
        if((needLevels.length == 0) || (needLevels == null)){
            return;
        }
        String currentLevel = (String) req.getSession().getAttribute("role");
        for (User.Role needLevel : needLevels) {
            if(needLevel.getValue().equals(currentLevel)){
                return;
            }
        }
        throw new UnauthorizedException("Not enough permissions for this operation");
    }

    /**
     * Method that perform all action of command.
     * @param req Instance of HttpServletRequest
     * @param resp Instance of HttpServletResponse
     * @throws ServletException
     * @throws IOException
     * @throws CommandException
     * @throws UnauthorizedException
     */
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException;
}
