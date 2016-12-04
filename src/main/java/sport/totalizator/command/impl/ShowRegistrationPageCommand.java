package sport.totalizator.command.impl;

import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.CategoryDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowRegistrationPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        CategoryDAOImpl categoryDAO = CategoryDAOImpl.getInstance();
        try {
            req.setAttribute("categories", categoryDAO.getAllCategories());
        }
        catch (DAOException exc){
            throw new CommandException(exc);
        }
        req.getRequestDispatcher("registration_page.jsp").forward(req, resp);
    }
}
