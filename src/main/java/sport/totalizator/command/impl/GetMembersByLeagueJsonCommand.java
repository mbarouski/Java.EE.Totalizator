package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.League;
import sport.totalizator.entity.Member;
import sport.totalizator.service.LeagueService;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.JsonSerializer;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sport.totalizator.util.JspPathes.JSON;

/**
 * {@link ICommand} implementaion that helps us to get list of
 * {@link Member} instances that belongs to {@link League} in JSON format.
 */
public class GetMembersByLeagueJsonCommand implements ICommand {
    private static final Logger log = Logger.getLogger(GetLeaguesByCategoryJsonCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        MemberService memberService = ServiceFactory.getInstance().getMemberService();
        int leagueId = NumberValidator.parseInt(req.getParameter("leagueId"), 0);
        List<Member> members = new ArrayList<Member>();
        try {
            members = memberService.getMembersByLeague(leagueId);
        } catch (ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        resp.setHeader("Content-type", "json");
        req.setAttribute("json", JsonSerializer.json(members));
        req.getRequestDispatcher(JSON).forward(req, resp);
    }

}
