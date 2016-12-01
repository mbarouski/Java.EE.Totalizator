package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final UserDAOImpl instance = new UserDAOImpl();
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static UserDAOImpl getInstance(){
        return instance;
    }

    public List<User> getAllUsers(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM `user`";
        List<User> result = new ArrayList<User>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            resultSet = statement.getResultSet();
            User user;
            while(resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassHash(resultSet.getString("pass_hash"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
                user.setBalance(resultSet.getBigDecimal("balance"));
                user.setBanned(resultSet.getBoolean("banned"));
                result.add(user);
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
