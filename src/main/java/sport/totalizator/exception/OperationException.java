package sport.totalizator.exception;

import sport.totalizator.entity.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationException extends ExceptionWithErrorList {
    private Operation operation;

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
        super();
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}
