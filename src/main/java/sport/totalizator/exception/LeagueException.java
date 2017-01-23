package sport.totalizator.exception;

import sport.totalizator.entity.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueException extends ExceptionWithErrorList {
    private League league;

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
        super();
        this.league = league;
    }

    public League getLeague() {
        return league;
    }
}
