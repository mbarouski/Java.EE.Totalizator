package sport.totalizator.exception;

import sport.totalizator.entity.EventResult;

import java.util.ArrayList;
import java.util.List;

public class EventResultException extends ExceptionWithErrorList {
    private EventResult eventResult;

    public EventResultException() {
        super();
    }

    public EventResultException(String message) {
        super(message);
    }

    public EventResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventResultException(Throwable cause) {
        super(cause);
    }

    public EventResultException(EventResult eventResult){
        super();
        this.eventResult = eventResult;
    }

    public EventResult getEventResult(){
        return eventResult;
    }
}
