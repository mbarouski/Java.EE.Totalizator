package sport.totalizator.exception;

import sport.totalizator.entity.Rate;

import java.util.ArrayList;
import java.util.List;

public class RateException extends ExceptionWithErrorList {
    private Rate rate;

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
        super();
        this.rate = rate;
    }

    public Rate getRate() {
        return rate;
    }
}
