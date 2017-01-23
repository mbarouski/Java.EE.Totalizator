package sport.totalizator.exception;

import sport.totalizator.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventException extends ExceptionWithErrorList {
    private Event event;

    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

    public EventException(Event event){
        super();
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
