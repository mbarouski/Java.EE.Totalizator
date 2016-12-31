package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.exception.UserException;
import sport.totalizator.service.UserService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.login(login, password);
            if(user != null){
                HttpSession session = req.getSession();
                session.setAttribute("username", login);
                session.setAttribute("role", user.getRole().getValue());
            }

        } catch (ServiceException exc){
            log.error(exc);
        } catch (UserException exc){
            req.setAttribute("user", exc.getUser());
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getMessage(), req));
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_LOGIN_PAGE).execute(req, resp);
        }

        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MAIN_PAGE).execute(req, resp);
    }
}
