package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.CategoryDAO;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO{
    private static final CategoryDAOImpl instance = new CategoryDAOImpl();
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static CategoryDAOImpl getInstance(){
        return instance;
    }

    public List<Category> getAllCategories(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `eventcategory`";
        List<Category> result = new ArrayList<Category>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            resultSet = statement.getResultSet();
            Category category;
            while(resultSet.next()){
                category = new Category();
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("category_name"));
                result.add(category);
            }
        }
        catch (SQLException exc){
            log.error(exc);
        }
        finally {
            pool.returnConnectionToPool(connection);
            try {
                resultSet.close();
            }
            catch (SQLException exc){
                log.error(exc);
            }
            try {
                statement.close();
            }
            catch (SQLException exc){
                log.error(exc);
            }
        }
        return result;
    }
}
