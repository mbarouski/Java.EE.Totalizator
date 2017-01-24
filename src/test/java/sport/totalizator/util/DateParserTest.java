package sport.totalizator.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateParserTest {
    @Test(expected = ParseException.class)
    public void parseInvalidDate() throws ParseException{
        DateParser.parse("invalid date");
    }

    @Test
    public void parseValidDate() throws ParseException{
        assertEquals(1485294000000L, DateParser.parse("2017/01/25 00:40").getTime());
    }
}
