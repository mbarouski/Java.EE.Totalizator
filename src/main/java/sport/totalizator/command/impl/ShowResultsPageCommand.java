package sport.totalizator.command.impl;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.EventDAOImpl;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.command.CommandEnum.SHOW_RESULTS_PAGE;
import static sport.totalizator.util.JspPathes.MAIN_PAGE;
import static sport.totalizator.util.PaginationObject.DEFAULT_PAGE;

/**
 * {@link ICommand} implementaion whose task is showing of page with finished {@link sport.totalizator.entity.Event}s.
 */
public class ShowResultsPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        req.setAttribute("tab_classes", new String[] {"", "active", "", ""});
        EventService eventService = ServiceFactory.getInstance().getEventService();
        int page = NumberValidator.parseInt(req.getParameter("page"), 1);
        try {
            req.setAttribute("events", eventService.getAllEndedEvents(page));
        }
        catch (ServiceException exc){
            throw new CommandException(exc);
        }
        req.setAttribute("command", SHOW_RESULTS_PAGE.getValue());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
