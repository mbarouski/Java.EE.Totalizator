package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Operation;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UserException;
import sport.totalizator.util.MessageLocalizer;

import java.math.BigDecimal;
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
    public User createUser(User user) throws DAOException, UserException {
        String sql = "INSERT INTO `user`(`login`, `pass_hash`, `email`) VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassHash());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
            } catch (SQLException exc) {
                connection.rollback(savepoint);
                log.error(exc);
                if (exc.getErrorCode() == 1062) {
                    throw new UserException("err.user-exists", user);
                } else {
                    throw new DAOException(exc);
                }
            } finally {
                connection.setAutoCommit(true);
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

    @Override
    public User getFullUserInformationByLogin(String login) throws DAOException {
        String sql = "SELECT `login` , `role`, `balance`, `user_id`, `banned`, `email` " +
                "FROM `user` " +
                "WHERE `login` = ?;";
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
                        user.setLogin(resultSet.getString("login"));
                        user.setBalance(resultSet.getBigDecimal("balance"));
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setEmail(resultSet.getString("email"));
                        user.setBanned(resultSet.getBoolean("banned"));
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

    @Override
    public int getUserIdByLogin(String login) throws DAOException {
        String sql = "SELECT `user_id` " +
                "FROM `user` " +
                "WHERE `login` = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userId = 0;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                try{
                    while (resultSet.next()){
                        userId = resultSet.getInt("user_id");
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
        return userId;
    }

    @Override
    public Operation fillUpBalanceForUser(Operation operation) throws DAOException {
        String sql = "UPDATE `user` " +
                "SET `balance` = `balance` + ? " +
                "WHERE `user_id` = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                statement = connection.prepareStatement(sql);
                statement.setBigDecimal(1, operation.getAmount());
                statement.setInt(2, operation.getUserId());
                statement.executeUpdate();
            } catch (SQLException exc) {
                connection.rollback(savepoint);
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                connection.setAutoCommit(true);
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
        return operation;
    }

    @Override
    public void withdrawMoneyFromUser(int userId, BigDecimal money) throws DAOException {
        String sql = "UPDATE `user` " +
                "SET `balance` = `balance` - ? " +
                "WHERE `user_id` = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                statement = connection.prepareStatement(sql);
                statement.setBigDecimal(1, money);
                statement.setInt(2, userId);
                statement.executeUpdate();
            } catch (SQLException exc) {
                connection.rollback(savepoint);
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                connection.setAutoCommit(true);
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
    }

    @Override
    public boolean haveMoney(int userId, BigDecimal money) throws DAOException {
        String sql = "SELECT `balance` >= ? AS `flag` " +
                "FROM `user` " +
                "WHERE `user_id` = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setBigDecimal(1, money);
                statement.setInt(2, userId);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    while(resultSet.next()){
                        result = resultSet.getBoolean("flag");
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
        return result;
    }
}
