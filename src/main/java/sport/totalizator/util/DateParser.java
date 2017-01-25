package sport.totalizator.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * {@link DateParser} is used for parsing some string to {@link Date} value.
 */
public class DateParser {
    public static Date parse(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return new java.sql.Date(df.parse(date).getTime());
    }
}
