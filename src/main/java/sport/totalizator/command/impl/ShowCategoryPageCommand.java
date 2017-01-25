package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.CategoryDAOImpl;
import sport.totalizator.dao.impl.EventDAOImpl;
import sport.totalizator.entity.Event;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static sport.totalizator.command.CommandEnum.SHOW_CATEGORY_PAGE;
import static sport.totalizator.util.JspPathes.MAIN_PAGE;
import static sport.totalizator.util.PaginationObject.DEFAULT_PAGE;

/**
 * {@link ICommand} implementaion whose task is showing of page with {@link Event}s of {@link sport.totalizator.entity.Category}.
 */
public class ShowCategoryPageCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddCategoriesToRequestCommand.class);

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        EventService eventService = ServiceFactory.getInstance().getEventService();
        int page = NumberValidator.parseInt(req.getParameter("page"), 1);
        String categoryId = req.getParameter("categoryId");
        try {
            req.setAttribute("events", eventService.getAllNotEndedEventsByCategoryId(categoryId, page));
        }
        catch (ServiceException exc){
            log.error(exc);
        }
        req.setAttribute("categoryPage", true);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("command", SHOW_CATEGORY_PAGE.getValue());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
