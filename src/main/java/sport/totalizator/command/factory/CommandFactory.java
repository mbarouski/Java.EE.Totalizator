package sport.totalizator.command.factory;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static sport.totalizator.command.CommandEnum.SHOW_MAIN_PAGE;
import static sport.totalizator.command.CommandEnum.SHOW_MOST_INTERESTING_EVENTS_PAGE_COMMAND;
import static sport.totalizator.command.CommandEnum.SHOW_NEAREST_EVENTS_PAGE_COMMAND;

public class CommandFactory {
    private static final CommandFactory factory = new CommandFactory();
    public static CommandFactory getFactory(){
        return factory;
    }

    private Map<CommandEnum, ICommand> commands;

    private CommandFactory(){
        commands = new HashMap<CommandEnum, ICommand>();
        commands.put(SHOW_MAIN_PAGE, new ShowMainPageCommand());
        commands.put(SHOW_NEAREST_EVENTS_PAGE_COMMAND, new ShowNearestEventsPageCommand());
        commands.put(SHOW_MOST_INTERESTING_EVENTS_PAGE_COMMAND, new ShowMostInterestingEventsPageCommand());
    }

    public ICommand createCommand(CommandEnum command){
        if(commands.containsKey(command)){
            return commands.get(command);
        }
        return null;
    }
}
