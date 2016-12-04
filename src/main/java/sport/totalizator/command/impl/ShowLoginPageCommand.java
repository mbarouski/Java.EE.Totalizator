package sport.totalizator.command.impl;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.CategoryDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLoginPageCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        req.getRequestDispatcher("login_page.jsp").forward(req, resp);
    }
}
