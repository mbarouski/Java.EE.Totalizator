package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.entity.User.Role.MODERATOR;

public class ShowAddEventResultPageCommand implements ICommand {
    private static final Logger log = Logger.getLogger(ShowAddEventResultPageCommand.class);
    private MemberService memberService = ServiceFactory.getInstance().getMemberService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(req, new User.Role[]{MODERATOR});
        String eventId = req.getParameter("eventId");
        try{
            int intEventId = Integer.parseInt(eventId);
            req.setAttribute("members", memberService.getMembersByEvent(intEventId));
            req.setAttribute("eventId", eventId);
        } catch (ServiceException | NumberFormatException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        req.getRequestDispatcher("add_result_page.jsp").forward(req, resp);
    }
}
