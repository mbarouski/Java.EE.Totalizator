package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.OperationException;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.PaySystemService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static sport.totalizator.entity.User.Role.ADMINISTRATOR;
import static sport.totalizator.entity.User.Role.MODERATOR;
import static sport.totalizator.entity.User.Role.USER;

public class FillUpBalanceCommand implements ICommand {
    private static final Logger log = Logger.getLogger(FillUpBalanceCommand.class);
    private PaySystemService paySystemService = ServiceFactory.getInstance().getPaySystemService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(req, new User.Role[]{MODERATOR, ADMINISTRATOR, USER});
        String cardNumber = req.getParameter("card-number");
        String cardCode = req.getParameter("card-code");
        String validityDate = req.getParameter("validity-date");
        String amount = req.getParameter("amount");
        try {
            paySystemService.fillUpBalance((String)req.getSession().getAttribute("username"), cardNumber, validityDate, cardCode, amount);
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (OperationException exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("operation", exc.getOperation());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_FILL_UP_BALANCE_PAGE).execute(req, resp);
        }
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.fill-up", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_FILL_UP_BALANCE_PAGE).execute(req, resp);
    }
}
