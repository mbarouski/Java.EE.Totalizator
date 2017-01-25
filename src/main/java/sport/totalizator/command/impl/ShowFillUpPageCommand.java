package sport.totalizator.command.impl;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.util.JspPathes.FILL_UP_BALANCE_PAGE;

/**
 * {@link ICommand} implementaion whose task is showing of page for filling up user's balance.
 */
public class ShowFillUpPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[] {User.Role.USER, User.Role.ADMINISTRATOR, User.Role.MODERATOR});
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        req.getRequestDispatcher(FILL_UP_BALANCE_PAGE).forward(req, resp);
    }
}
