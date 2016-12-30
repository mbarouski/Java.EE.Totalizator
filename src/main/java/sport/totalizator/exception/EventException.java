package sport.totalizator.exception;

import sport.totalizator.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventException extends Exception {
    private Event event;
    private List<String> errorMessageList;

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
        this.errorMessageList = new ArrayList<>();
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public List<String> getErrorMessageList(){
        return errorMessageList;
    }

    public void addErrorMessage(String message){
        errorMessageList.add(message);
    }
}
