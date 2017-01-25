package sport.totalizator.command.factory;

import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static sport.totalizator.command.CommandEnum.*;

/**
 * CommandFactory is instance of Factory pattern that helps us
 * to get implementation of {@link ICommand} interface by enum {@link CommandEnum}.
 * Singleton.
 */
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
        commands.put(FILL_UP_BALANCE, new FillUpBalanceCommand());
        commands.put(WITHDRAW_MONEY, new WithdrawMoneyCommand());
        commands.put(GET_MEMBERS_BY_EVENT_JSON, new GetMembersByEventJsonCommand());
        commands.put(MAKE_RATE, new MakeRateCommand());
        commands.put(SHOW_ADD_EVENT_RESULT_PAGE, new ShowAddEventResultPageCommand());
        commands.put(ADD_EVENT_RESULT, new AddEventResultCommand());
        commands.put(SHOW_ADD_CATEGORY_PAGE, new ShowAddCategoryPageCommand());
        commands.put(ADD_CATEGORY, new AddCategoryCommand());
        commands.put(ADD_LEAGUE, new AddLeagueCommand());
        commands.put(ADD_MEMBER, new AddMemberCommand());
        commands.put(SHOW_ADD_MEMBER_PAGE, new ShowAddMemberPageCommand());
        commands.put(SHOW_ADD_LEAGUE_PAGE, new ShowAddLeaguePageCommand());
        commands.put(SHOW_ADMIN_PAGE, new ShowAdminPageCommand());
        commands.put(BAN, new BanCommand());
        commands.put(UNBAN, new UnbanCommand());
        commands.put(CHANGE_ROLE, new ChangeRoleCommand());
        commands.put(SEARCH_EVENT, new SearchEventCommand());
    }

    /**
     * Method returns us {@link ICommand} implementation by {@link CommandEnum} value.
     * @param command {@link CommandEnum} value that identifies {@link ICommand} implementation.
     * @return {@link ICommand} implementation.
     */
    public ICommand createCommand(CommandEnum command){
        if(commands.containsKey(command)){
            return commands.get(command);
        }
        return null;
    }
}
