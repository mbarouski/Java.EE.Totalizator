package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
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

import static sport.totalizator.command.CommandEnum.SEARCH_EVENT;
import static sport.totalizator.command.CommandEnum.SHOW_CATEGORY_PAGE;
import static sport.totalizator.util.JspPathes.MAIN_PAGE;
import static sport.totalizator.util.PaginationObject.DEFAULT_PAGE;

/**
 * {@link ICommand} implementaion that performs searching of events.
 */
public class SearchEventCommand implements ICommand {
    private static final Logger log = Logger.getLogger(SearchEventCommand.class);
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        int page = NumberValidator.parseInt(req.getParameter("page"), DEFAULT_PAGE);
        String searchQuery = req.getParameter("search");
        try {
            req.setAttribute("events", eventService.searchEvents(searchQuery, page));
        }
        catch (ServiceException exc){
            log.error(exc);
        }
        req.setAttribute("searchPage", true);
        req.setAttribute("search", searchQuery);
        req.setAttribute("command", SEARCH_EVENT.getValue());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
