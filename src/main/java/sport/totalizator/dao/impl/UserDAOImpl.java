package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final UserDAOImpl instance = new UserDAOImpl();
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    private final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static UserDAOImpl getInstance(){
        return instance;
    }

    public List<User> getAllUsers() throws DAOException {
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

    @Override
    public User createUser(User user) throws DAOException {
        String sql = "INSERT INTO `user`(`login`, `pass_hash`, `email`) VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassHash());
                statement.setString(3, user.getEmail());
                int result = statement.executeUpdate();
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        String sql = "SELECT `login`, `pass_hash` , `role` " +
                "FROM `user` " +
                "WHERE `login` = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                try{
                    while (resultSet.next()){
                        user = new User();
                        user.setPassHash(resultSet.getString("pass_hash"));
                        user.setLogin(resultSet.getString("login"));
                        user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
                    }
                } catch (SQLException exc){
                    log.error(exc);
                    throw new DAOException(exc);
                } finally {
                    if(resultSet != null){
                        resultSet.close();
                    }
                }
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }
        return user;
    }
}
