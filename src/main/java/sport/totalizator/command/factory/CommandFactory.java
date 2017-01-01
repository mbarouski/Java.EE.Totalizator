package sport.totalizator.command.factory;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static sport.totalizator.command.CommandEnum.*;

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
        commands.put(SHOW_CATEGORY_PAGE, new ShowCategoryPageCommand());
        commands.put(SHOW_REGISTRATION_PAGE, new ShowRegistrationPageCommand());
        commands.put(REGISTER, new RegisterCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(SHOW_LOGIN_PAGE, new ShowLoginPageCommand());
        commands.put(ADD_CATEGORIES_TO_REQUEST, new AddCategoriesToRequestCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(SHOW_RESULTS_PAGE, new ShowResultsPageCommand());
        commands.put(SHOW_EVENT_PAGE, new ShowEventPageCommand());
        commands.put(SHOW_ADD_EVENT_PAGE, new ShowAddEventPageCommand());
        commands.put(GET_LEAGUES_BY_CATEGORY_JSON, new GetLeaguesByCategoryJsonCommand());
        commands.put(GET_MEMBERS_BY_LEAGUE_JSON, new GetMembersByLeagueJsonCommand());
        commands.put(ADD_EVENT, new AddEventCommand());
        commands.put(SHOW_MAKE_RATE_PAGE, new ShowMakeRatePageCommand());
        commands.put(SHOW_PERSONAL_PAGE, new ShowPersonalPageCommand());
        commands.put(SHOW_FILL_UP_BALANCE_PAGE, new ShowFillUpPageCommand());
        commands.put(SHOW_WITHDRAW_MONEY_PAGE, new ShowWithdrawMoneyPage());
    }

    public ICommand createCommand(CommandEnum command){
        if(commands.containsKey(command)){
            return commands.get(command);
        }
        return null;
    }
}
