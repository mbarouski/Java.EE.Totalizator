package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.entity.User.Role.MODERATOR;
import static sport.totalizator.util.JspPathes.ADD_RESULT_PAGE;

/**
 * {@link ICommand} implementaion whose task is showing of add {@link sport.totalizator.entity.EventResult} page.
 */
public class ShowAddEventResultPageCommand implements ICommand {
    private static final Logger log = Logger.getLogger(ShowAddEventResultPageCommand.class);
    private MemberService memberService = ServiceFactory.getInstance().getMemberService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[]{MODERATOR});
        CommandFactory.getFactory().createCommand(CommandEnum.ADD_CATEGORIES_TO_REQUEST).execute(req, resp);
        String eventId = req.getParameter("eventId");
        if(eventId == null){
            eventId = (String)req.getAttribute("eventId");
        }
        int intEventId = NumberValidator.parseInt(eventId, 0);
        req.setAttribute("eventId", eventId);
        try{
            req.setAttribute("members", memberService.getMembersByEvent(intEventId));
        } catch (ServiceException | NumberFormatException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        req.getRequestDispatcher(ADD_RESULT_PAGE).forward(req, resp);
    }
}
