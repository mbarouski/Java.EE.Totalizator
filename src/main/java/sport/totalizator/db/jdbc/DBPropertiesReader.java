package sport.totalizator.db.jdbc;

import java.util.Properties;
import java.util.ResourceBundle;

public class DBPropertiesReader {
    public static Properties getDBProperties(){
        Properties result = new Properties();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        result.setProperty("url", resource.getString("url"));
        result.setProperty("username", resource.getString("username"));
        result.setProperty("password", resource.getString("password"));
        result.setProperty("poolSize", resource.getString("poolSize"));
        return result;
    }
}
