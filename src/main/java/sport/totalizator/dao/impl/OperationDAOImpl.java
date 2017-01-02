package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.OperationDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.OperationException;
import sport.totalizator.exception.UserException;

import java.sql.*;

public class OperationDAOImpl implements OperationDAO {
    public static final String INPUT = "INPUT";
    public static final String OUTPUT = "OUTPUT";

    private static final Logger log = Logger.getLogger(OperationDAOImpl.class);
    private static final OperationDAOImpl instance = new OperationDAOImpl();
    private final ConnectionPool pool = ConnectionPool.getConnectionPool();

    private OperationDAOImpl(){}

    public static OperationDAOImpl getInstance(){
        return instance;
    }

    @Override
    public Operation addOperation(Operation operation) throws DAOException, OperationException {
        String sql = "INSERT INTO `moneyoperation`(`user_id`, `operation_type`, `amount`, `card_number`, `validity_date`) " +
                "VALUES(?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, operation.getUserId());
                statement.setString(2, operation.getOperationType());
                statement.setBigDecimal(3, operation.getAmount());
                statement.setString(4, operation.getCardNumber());
                statement.setString(5, operation.getValidityDate());
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
    public boolean canFillUpBalanceForUser(int userId) throws DAOException {
        String sql = "SELECT (TO_DAYS(now()) - TO_DAYS(`date`)) > 1 AS `flag` " +
                "FROM `moneyoperation` " +
                "WHERE `user_id` = ? " +
                "ORDER BY `date` " +
                "DESC " +
                "LIMIT 1;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);
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

    @Override
    public boolean canWithdrawMoney(Operation operation) throws DAOException {
        return false;
    }
}
