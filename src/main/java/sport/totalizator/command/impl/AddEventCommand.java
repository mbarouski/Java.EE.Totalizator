package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;
import sport.totalizator.util.NumberValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sport.totalizator.entity.User.Role.MODERATOR;

/**
 * {@link ICommand} implementaion that performs adding new {@link Event} instance to database.
 */
public class AddEventCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddEventCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[]{MODERATOR});
        EventService eventService = ServiceFactory.getInstance().getEventService();
        String name = req.getParameter("name");
        String leagueId = req.getParameter("league-id");
        String liveTranslationLink = req.getParameter("liveTranslation");
        String date = req.getParameter("date");
        List<Integer> memberIds = getMemberIds(req);
        Event event = null;
        try {
            event = eventService.addEvent(name, leagueId, liveTranslationLink, date, memberIds);
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (ExceptionWithErrorList exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("event", exc.getCauseObject());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_EVENT_PAGE).execute(req, resp);
        }
        req.setAttribute("event", Object.class.cast(event));
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.add-event", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_EVENT_PAGE).execute(req, resp);
    }

    private List<Integer> getMemberIds(HttpServletRequest req){
        List<Integer> memberIds = new ArrayList<>();
        String memberSelectName = "member-select-";
        int i = 1;
        int number;
        String memberId;
        try {
            while ((memberId = req.getParameter(memberSelectName + i)) != null) {
                number = NumberValidator.parseInt(memberId);
                //todo -> check ids in service method
                memberIds.add(number);
                i++;
            }
        } catch (NumberFormatException exc){
            log.error(exc);
        }
        return memberIds;
    }
}
