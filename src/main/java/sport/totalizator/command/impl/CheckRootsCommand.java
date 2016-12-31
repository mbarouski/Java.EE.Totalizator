package sport.totalizator.command.impl;

import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UnauthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckRootsCommand implements ICommand {

    private User.Role needLevel;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        if(needLevel == null){
            return;
        }
        String needLevelInString = needLevel.getValue();
        String roleFromSession = (String)req.getSession().getAttribute("role");
        if(!needLevelInString.equals(roleFromSession)){
            throw new UnauthorizedException("Not enough roots for this operation");
        }
    }

    public void setNeedLevel(User.Role needLevel){
        this.needLevel = needLevel;
    }

}
