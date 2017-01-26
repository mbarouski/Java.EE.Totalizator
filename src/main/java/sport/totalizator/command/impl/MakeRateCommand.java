package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.Rate;
import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.RateService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.entity.User.Role.ADMINISTRATOR;
import static sport.totalizator.entity.User.Role.MODERATOR;
import static sport.totalizator.entity.User.Role.USER;

/**
 * {@link ICommand} implementaion that performs rate making.
 */
public class MakeRateCommand implements ICommand{
    private static final Logger log = Logger.getLogger(MakeRateCommand.class);
    private RateService rateService = ServiceFactory.getInstance().getRateService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[]{MODERATOR, ADMINISTRATOR, USER});
        String type = req.getParameter("rate-type");
        String money = req.getParameter("money");
        String eventId = req.getParameter("event-id");
        String member1Id = req.getParameter("member-1");
        String member2Id = req.getParameter("member-2");
        String member1Score = req.getParameter("member-1-score");
        String member2Score = req.getParameter("member-2-score");
        String username = (String)req.getSession().getAttribute("username");
        Rate rate = null;
        try {
            rate = rateService.makeRate(type, eventId, username, money, member1Id, member1Score, member2Id, member2Score);
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (ExceptionWithErrorList exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("rate", exc.getCauseObject());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MAKE_RATE_PAGE).execute(req, resp);
        }
        req.setAttribute("eventId", eventId);
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.rate-maked", req));
        req.setAttribute("rate", rate);
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MAKE_RATE_PAGE).execute(req, resp);
    }
}
