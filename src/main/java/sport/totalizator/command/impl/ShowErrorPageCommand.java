package sport.totalizator.command.impl;

import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorPageCommand implements ICommand {
    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("error_page.jsp").forward(req, resp);
    }
}
