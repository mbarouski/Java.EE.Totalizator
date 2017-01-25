package sport.totalizator.command;

/**
 * There are all constants that identifies commands in this enum.
 */
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
    WITHDRAW_MONEY("withdrawMoney"),
    GET_MEMBERS_BY_EVENT_JSON("getMembersByEventJson"),
    MAKE_RATE("makeRate"),
    SHOW_ADD_EVENT_RESULT_PAGE("showAddEventResultPage"),
    ADD_EVENT_RESULT("addEventResult"),
    SHOW_ADD_CATEGORY_PAGE("showAddCategoryPage"),
    ADD_LEAGUE("addLeague"),
    ADD_MEMBER("addMember"),
    ADD_CATEGORY("addCategory"),
    SHOW_ADD_LEAGUE_PAGE("showAddLeaguePage"),
    SHOW_ADD_MEMBER_PAGE("showAddMemberPage"),
    SHOW_ADMIN_PAGE("showAdminPage"),
    BAN("ban"),
    UNBAN("unban"),
    CHANGE_ROLE("changeRole"),
    SEARCH_EVENT("searchEvent");


    private String value;

    private CommandEnum(String value){
        this.value = value;
    }

    /**
     * Method that allows us to get string value of enum.
     * @return String value for enum.
     */
    public String getValue(){
        return value;
    }


    /**
     * Mathod that allows us to get enum value by string or get default value if it didn't find relevant enum value.
     * @param value String value for enum.
     * @return CommandEnum value.
     * @throws IllegalArgumentException
     */
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
