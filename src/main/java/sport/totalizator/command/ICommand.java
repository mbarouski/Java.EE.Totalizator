package sport.totalizator.command;

import sport.totalizator.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException;
}
