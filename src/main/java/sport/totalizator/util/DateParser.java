package sport.totalizator.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {
    public static Date parse(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return new java.sql.Date(df.parse(date).getTime());
    }
}
