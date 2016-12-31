package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.CategoryDAOImpl;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoriesToRequestCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddCategoriesToRequestCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        CategoryDAOImpl categoryDAO = CategoryDAOImpl.getInstance();
        try{
            req.setAttribute("categories", categoryDAO.getAllCategories());
        } catch (DAOException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
    }
}
