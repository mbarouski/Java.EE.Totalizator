package sport.totalizator.command;

public enum CommandEnum {
    SHOW_MAIN_PAGE("showMainPage"),
    SHOW_ERROR_PAGE("showErrorPage"),
    SHOW_NEAREST_EVENTS_PAGE_COMMAND("showNearestEventsPage"),
    SHOW_MOST_INTERESTING_EVENTS_PAGE_COMMAND("showMostInterestingEventsPage"),
    SHOW_CATEGORY_PAGE("showCategoryPage"),
    SHOW_REGISTRATION_PAGE("showRegistrationPage"),
    REGISTER("register"),
    LOGIN("login"),
    SHOW_LOGIN_PAGE("showLoginPage"),
    ADD_CATEGORIES_TO_REQUEST("addCategoriesToRequest"),
    LOGOUT("logout"),
    CHANGE_LOCALE("changeLocale"),
    SHOW_RESULTS_PAGE("showResultsPage"),
    SHOW_EVENT_PAGE("showEventPage"),
    SHOW_ADD_EVENT_PAGE("showAddEventPage"),
    GET_LEAGUES_BY_CATEGORY_JSON("getLeaguesByCategoryJson"),
    GET_MEMBERS_BY_LEAGUE_JSON("getMembersByLeagueJson"),
    ADD_EVENT("addEvent"),
    SHOW_MAKE_RATE_PAGE("showMakeRatePage"),
    SHOW_PERSONAL_PAGE("showPersonalPage"),
    SHOW_FILL_UP_BALANCE_PAGE("showFillUpBalancePage"),
    SHOW_WITHDRAW_MONEY_PAGE("showWithdrawMoneyPage"),
    FILL_UP_BALANCE("fillUpBalance"),
    WITHDRAW_MONEY("withdrawMoney");


    private String value;

    private CommandEnum(String value){
        this.value = value;
    }

    public static CommandEnum getEnum(String value) throws IllegalArgumentException{
        for(CommandEnum commandEnum : values()){
            if(commandEnum.value.equals(value)){
                return commandEnum;
            }
        }
        return getDefault();
    }

    private static CommandEnum getDefault(){
        return SHOW_MAIN_PAGE;
    }
}
