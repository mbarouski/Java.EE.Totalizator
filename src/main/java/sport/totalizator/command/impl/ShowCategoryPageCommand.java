package sport.totalizator.command.impl;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.CategoryDAOImpl;
import sport.totalizator.dao.impl.EventDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowCategoryPageCommand implements ICommand {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        EventDAO eventDAO = EventDAOImpl.getInstance();
        try {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            req.setAttribute("events", eventDAO.getNotEndedEventsByCategoryId(categoryId));
        }
        catch (DAOException | NumberFormatException exc){
            throw new CommandException(exc);
        }
        req.getRequestDispatcher("main_page.jsp").forward(req, resp);
    }
}