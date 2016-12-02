package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.CategoryDAO;
import sport.totalizator.dao.exception.DAOException;
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

    public List<Category> getAllCategories() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `eventcategory`";
        List<Category> result = new ArrayList<Category>();
        try {
            connection = pool.getConnection();
            try {
                statement = connection.createStatement();
                statement.execute(sql);
                try {
                    resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        result.add(createCategory(resultSet));
                    }
                } catch (SQLException exc) {
                    log.error(exc);
                    throw new DAOException(exc.getMessage());
                } finally {
                    if(resultSet != null){
                        resultSet.close();
                    }
                }
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc.getMessage());
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc.getMessage());
        } finally {
            pool.returnConnectionToPool(connection);
        }
        return result;
    }

    private Category createCategory(ResultSet resultSet) throws SQLException{
        Category category = new Category();
        category.setId(resultSet.getInt("category_id"));
        category.setName(resultSet.getString("category_name"));
        return category;
    }
}
