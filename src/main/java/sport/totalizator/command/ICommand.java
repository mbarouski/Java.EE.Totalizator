package sport.totalizator.command;

import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.command.impl.CheckRootsCommand;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {
    default void checkRoots(HttpServletRequest req, HttpServletResponse resp, User.Role needLevel)
            throws ServletException, IOException, CommandException, UnauthorizedException{
        CheckRootsCommand checkRootsCommand = (CheckRootsCommand) CommandFactory.getFactory().createCommand(CommandEnum.CHECK_ROOTS);
        checkRootsCommand.setNeedLevel(User.Role.MODERATOR);
        checkRootsCommand.execute(req, resp);
    }

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException;
}
