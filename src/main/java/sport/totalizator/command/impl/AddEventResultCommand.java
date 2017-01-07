package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.EventResultException;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.EventResultService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddEventResultCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddEventResultCommand.class);
    EventResultService eventResultService = ServiceFactory.getInstance().getEventResultService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        String eventId = req.getParameter("event-id");
        String winnerId = req.getParameter("winner-id");
        String winnerScore = req.getParameter("winner-score");
        String loserId = req.getParameter("loser-id");
        String loserScore = req.getParameter("loser-score");
        try{
            eventResultService.addEventResult(eventId, winnerId, loserId, winnerScore, loserScore);
        } catch (ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        } catch (EventResultException exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("eventResult", exc.getEventResult());
            req.getRequestDispatcher("add_result_page.jsp").forward(req, resp);
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_EVENT_RESULT_PAGE).execute(req, resp);
        }
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.add-event-result", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_EVENT_RESULT_PAGE).execute(req, resp);
    }
}
