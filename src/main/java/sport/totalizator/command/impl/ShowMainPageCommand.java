package sport.totalizator.command.impl;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.command.CommandEnum.SHOW_MAIN_PAGE;
import static sport.totalizator.util.JspPathes.MAIN_PAGE;
import static sport.totalizator.util.PaginationObject.DEFAULT_PAGE;

public class ShowMainPageCommand implements ICommand {
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        req.setAttribute("tab_classes", new String[] {"active", "", "", ""});
        req.setAttribute("command", SHOW_MAIN_PAGE.getValue());
        int page = NumberValidator.parseInt(req.getParameter("page"), 1);
        try {
            req.setAttribute("events", eventService.getAllNotEndedEvents(page));
        }
        catch (ServiceException exc){
            throw new CommandException(exc);
        }
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
