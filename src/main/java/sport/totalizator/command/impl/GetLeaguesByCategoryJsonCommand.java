package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.League;
import sport.totalizator.service.LeagueService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.JsonSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sport.totalizator.util.JspPathes.JSON;

public class GetLeaguesByCategoryJsonCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetLeaguesByCategoryJsonCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        LeagueService leagueService = ServiceFactory.getInstance().getLeagueService();
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        List<League> leagues = new ArrayList<>();
        try {
            leagues = leagueService.getLeaguesByCategory(categoryId);
        } catch (ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        resp.setHeader("Content-type", "json");
        req.setAttribute("json", JsonSerializer.json(leagues));
        req.getRequestDispatcher(JSON).forward(req, resp);
    }
}
