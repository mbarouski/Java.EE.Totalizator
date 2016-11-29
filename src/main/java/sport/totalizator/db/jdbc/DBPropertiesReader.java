package sport.totalizator.db.jdbc;

import java.util.Properties;
import java.util.ResourceBundle;

public class DBPropertiesReader {
    public static Properties getDBProperties(){
        Properties result = new Properties();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String username = resource.getString("username");
        String password = resource.getString("password");
        String poolSize = resource.getString("poolSize");
        result.setProperty("url", url);
        result.setProperty("username", username);
        result.setProperty("password", password);
        result.setProperty("url", poolSize);
        return result;
    }
}
