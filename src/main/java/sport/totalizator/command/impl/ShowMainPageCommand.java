package sport.totalizator.command.impl;

import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.impl.CategoryDAOImpl;
import sport.totalizator.dao.impl.EventDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowMainPageCommand implements ICommand {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        EventDAO eventDAO = EventDAOImpl.getInstance();
        CategoryDAOImpl categoryDAO = CategoryDAOImpl.getInstance();
        req.setAttribute("events", eventDAO.getAllEvents());
        req.setAttribute("categories", categoryDAO.getAllCategories());
        req.getRequestDispatcher("main_page.jsp").forward(req, resp);
    }
}
