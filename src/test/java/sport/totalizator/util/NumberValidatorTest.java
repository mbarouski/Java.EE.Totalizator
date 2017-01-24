package sport.totalizator.util;

import org.junit.Test;
import sport.totalizator.exception.ExceptionWithErrorList;

import static org.junit.Assert.assertEquals;

public class NumberValidatorTest {
    @Test
    public void parseInvalidIntWithDefaultValueTest(){
        assertEquals(1, NumberValidator.parseInt("invalid int", 1));
    }

    @Test(expected = ExceptionWithErrorList.class)
    public void parseInvalidIntWithException() throws ExceptionWithErrorList{
        ExceptionWithErrorList exc = new ExceptionWithErrorList(new Object());
        NumberValidator.parseInt("invalid int", exc, "invalid int");
        if(exc.getErrorMessageList().size() > 0){
            throw exc;
        }
    }

    @Test
    public void parseInvalidIntTest(){
        assertEquals(-1, NumberValidator.parseInt("invalid int"));
    }

    @Test
    public void parseValidIntTest(){
        assertEquals(25, NumberValidator.parseInt("25"));
    }
}
