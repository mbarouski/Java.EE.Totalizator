package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.PaySystemService;
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
 * {@link ICommand} implementaion that performs withdrawing money process.
 */
public class WithdrawMoneyCommand implements ICommand {
    private static final Logger log = Logger.getLogger(WithdrawMoneyCommand.class);
    private PaySystemService paySystemService = ServiceFactory.getInstance().getPaySystemService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[]{MODERATOR, ADMINISTRATOR, USER});
        String cardNumber = req.getParameter("card-number");
        String validityDate = req.getParameter("validity-date");
        String amount = req.getParameter("amount");
        try {
            paySystemService.withdrawMoney((String)req.getSession().getAttribute("username"), cardNumber, validityDate, amount);
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (ExceptionWithErrorList exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("operation", exc.getCauseObject());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_WITHDRAW_MONEY_PAGE).execute(req, resp);
        }
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.withdraw", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_WITHDRAW_MONEY_PAGE).execute(req, resp);
    }
}
