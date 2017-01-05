package sport.totalizator.exception;

import sport.totalizator.entity.Rate;

import java.util.ArrayList;
import java.util.List;

public class RateException extends Exception {
    private Rate rate;
    private List<String> errorMessageList;

    public RateException() {
        super();
    }

    public RateException(String message) {
        super(message);
    }

    public RateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateException(Throwable cause) {
        super(cause);
    }

    public RateException(Rate rate){
        errorMessageList = new ArrayList<>();
        this.rate = rate;
    }

    public List<String> getErrorMessageList(){
        return errorMessageList;
    }

    public void addErrorMessage(String message){
        errorMessageList.add(message);
    }

    public Rate getRate() {
        return rate;
    }
}
