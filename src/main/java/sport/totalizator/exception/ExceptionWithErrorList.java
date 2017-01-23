package sport.totalizator.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionWithErrorList extends Exception {
    private Class typeOfCauseObject;
    private Object causeObject;

    public ExceptionWithErrorList() {
        super();
    }

    public ExceptionWithErrorList(Object causeObject, Class typeOfCauseObject) {
        super();
        this.typeOfCauseObject = typeOfCauseObject;
        this.causeObject = causeObject;
        this.errorMessageList = new ArrayList<>();
    }

    public ExceptionWithErrorList(String message) {
        super(message);
    }

    public ExceptionWithErrorList(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionWithErrorList(Throwable cause) {
        super(cause);
    }

    private List<String> errorMessageList;

    public void addMessage(String message){
        this.errorMessageList.add(message);
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public Object getCauseObject(){
        return this.causeObject;
    }

    public Class getTypeOfCauseObject(){
        return this.typeOfCauseObject;
    }
}
