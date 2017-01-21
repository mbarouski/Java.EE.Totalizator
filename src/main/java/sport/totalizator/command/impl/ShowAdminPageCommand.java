package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.UserService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.util.JspPathes.ADMIN_PAGE;

public class ShowAdminPageCommand implements ICommand {
    private static final Logger log = Logger.getLogger(ShowAdminPageCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(req, new User.Role[]{User.Role.ADMINISTRATOR});
        req.setAttribute("tab_classes", new String[] {"", "", "", "active"});
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        try{
            req.setAttribute("users", userService.getAllUsers());
        } catch (ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
    }
}
