package sport.totalizator.command;

import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {
    default void checkRoots(HttpServletRequest req, User.Role[] needLevels)
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
        throw new UnauthorizedException("Not enough roots for this operation");
    }

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException;
}
