package sport.totalizator.exception;

import sport.totalizator.entity.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueException extends Exception {
    private League league;
    private List<String> errorMessageList;

    public LeagueException() {
        super();
    }

    public LeagueException(String message) {
        super(message);
    }

    public LeagueException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeagueException(Throwable cause) {
        super(cause);
    }

    public LeagueException(League league){
        this.errorMessageList = new ArrayList<>();
        this.league = league;
    }

    public void addMessage(String message){
        this.errorMessageList.add(message);
    }

    public League getLeague() {
        return league;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }
}
