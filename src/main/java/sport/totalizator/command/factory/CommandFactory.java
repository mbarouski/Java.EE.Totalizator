package sport.totalizator.command.factory;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.impl.ShowErrorPageCommand;
import sport.totalizator.command.impl.ShowMainPageCommand;
import sport.totalizator.command.impl.ShowNearestEventsPageCommand;

public class CommandFactory {
    private static final CommandFactory factory = new CommandFactory();

    public static CommandFactory getFactory(){
        return factory;
    }

    private CommandFactory(){}

    public ICommand createCommand(CommandEnum command){
        switch (command){
            case SHOW_MAIN_PAGE:
                return new ShowMainPageCommand();
            case SHOW_ERROR_PAGE:
                return new ShowErrorPageCommand();
            case SHOW_NEAREST_EVENTS_PAGE_COMMAND:
                return new ShowNearestEventsPageCommand();
            default:
                return null;
        }
    }
}
