package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Rate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RateDAOImpl implements RateDAO{
    private static final Logger log = Logger.getLogger(RateDAOImpl.class);
    private static final RateDAOImpl instance = new RateDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static RateDAOImpl getInstance(){
        return instance;
    }

    RateDAOImpl(){}

    @Override
    public List<Rate> getActiveRatesForUser(int userId) throws DAOException {
        List<Rate> result = new ArrayList<>();
        String sql = "SELECT `rate`.`event_id`, `money`, `event_name`, `win_money`, `date` " +
                "FROM `rate` " +
                "JOIN `event` " +
                "ON `rate`.`event_id` = `event`.`event_id` " +
                "WHERE `user_id` = ? " +
                "AND `win_money` IS NULL " +
                "ORDER BY `date`;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    Rate rate;
                    while(resultSet.next()){
                        rate = new Rate();
                        rate.setEventId(resultSet.getInt("event_id"));
                        rate.setSum(resultSet.getBigDecimal("money"));
                        rate.setEventName(resultSet.getString("event_name"));
                        result.add(rate);
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
    public List<Rate> getFinishedRatesForUser(int userId) throws DAOException {
        List<Rate> result = new ArrayList<>();
        String sql = "SELECT `rate`.`event_id`, `money`, `event_name`, `win_money`, `date` " +
                "FROM `rate` " +
                "JOIN `event` " +
                "ON `rate`.`event_id` = `event`.`event_id` " +
                "WHERE `user_id` = ? " +
                "AND `win_money` IS NOT NULL " +
                "ORDER BY `date`;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, userId);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    Rate rate;
                    while(resultSet.next()){
                        rate = new Rate();
                        rate.setEventId(resultSet.getInt("event_id"));
                        rate.setSum(resultSet.getBigDecimal("money"));
                        rate.setEventName(resultSet.getString("event_name"));
                        rate.setWin(resultSet.getBigDecimal("win_money"));
                        result.add(rate);
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
