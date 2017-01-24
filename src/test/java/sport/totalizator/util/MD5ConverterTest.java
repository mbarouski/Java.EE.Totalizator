package sport.totalizator.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MD5ConverterTest {
    private static final Logger log = Logger.getLogger(MD5ConverterTest.class);

    @Test
    public void convertTest(){
        assertEquals("c74318b61a3024520c466f828c043c79", MD5Converter.getHash("md5_test"));
    }

}
