package sport.totalizator.exception;

import sport.totalizator.entity.EventResult;

import java.util.ArrayList;
import java.util.List;

public class EventResultException extends Exception {
    private List<String> errorMessageList;
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
        this.eventResult = eventResult;
        this.errorMessageList = new ArrayList<>();
    }

    public EventResult getEventResult(){
        return eventResult;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public void addErrorMessage(String message){
        errorMessageList.add(message);
    }
}
