package sport.totalizator.controller;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.util.JspPathes.ERROR_PAGE;


public class MainController extends HttpServlet {
    private static final CommandFactory commandFactory = CommandFactory.getFactory();
    private final static Logger log = Logger.getLogger(MainController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICommand command = null;
        String commandName = req.getParameter("command");
        try {
            CommandEnum commandEnum = CommandEnum.getEnum(commandName);
            command = commandFactory.createCommand(commandEnum);
            command.execute(req, resp);
        } catch (UnauthorizedException exc){
            log.error(exc);
            req.setAttribute("message", exc.getMessage());
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (Exception exc){
            log.error(exc);
            req.setAttribute("message", MessageLocalizer.getLocalizedForCurrentLocaleMessage("err.smth-error", req));
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
