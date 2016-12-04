package sport.totalizator.command;

public enum CommandEnum {
    SHOW_MAIN_PAGE("showMainPage"),
    SHOW_ERROR_PAGE("showErrorPage"),
    SHOW_NEAREST_EVENTS_PAGE_COMMAND("showNearestEventsPage"),
    SHOW_MOST_INTERESTING_EVENTS_PAGE_COMMAND("showMostInterestingEventsPage"),
    SHOW_CATEGORY_PAGE("showCategoryPage"),
    SHOW_REGISTRATION_PAGE("showRegistrationPage");

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
