package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.Member;
import sport.totalizator.exception.UnauthorizedException;
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

public class GetMembersByEventJsonCommand implements ICommand{
    private static final Logger log = Logger.getLogger(GetMembersByEventJsonCommand.class);
    private MemberService memberService = ServiceFactory.getInstance().getMemberService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        int eventId = NumberValidator.parseInt(req.getParameter("eventId"), 0);
        List<Member> members = new ArrayList<Member>();
        try {
            members = memberService.getMembersByEvent(eventId);
        } catch (ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        resp.setHeader("Content-type", "json");
        req.setAttribute("json", JsonSerializer.json(members));
        req.getRequestDispatcher(JSON).forward(req, resp);
    }
}



