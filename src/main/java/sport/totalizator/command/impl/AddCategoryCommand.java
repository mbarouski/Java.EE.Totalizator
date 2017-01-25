package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.Category;
import sport.totalizator.entity.User;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.CategoryService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.entity.User.Role.MODERATOR;

/**
 * {@link ICommand} implementaion that performs adding new {@link Category} instance to database.
 */
public class AddCategoryCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddCategoryCommand.class);
    private final CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkPermissions(req, new User.Role[]{MODERATOR});
        try {
            categoryService.addCategory((String)req.getParameter("name"));
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (ExceptionWithErrorList exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("category", exc.getCauseObject());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_CATEGORY_PAGE).execute(req, resp);
        }
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.category-is-added", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_CATEGORY_PAGE).execute(req, resp);
    }
}
