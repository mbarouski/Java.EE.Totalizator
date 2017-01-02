package sport.totalizator.exception;

import sport.totalizator.entity.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationException extends Exception {
    private Operation operation;
    private List<String> errorMessageList;

    public OperationException() {
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

    public OperationException(Operation operation){
        errorMessageList = new ArrayList<>();
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public List<String> getErrorMessageList(){
        return errorMessageList;
    }

    public void addErrorMessage(String message){
        errorMessageList.add(message);
    }
}
