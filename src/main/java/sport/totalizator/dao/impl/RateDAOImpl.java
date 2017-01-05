package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Rate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sport.totalizator.entity.Rate.*;

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

    @Override
    public Rate addRate(Rate rate) throws DAOException {
        Map<String, String> sqlMap = new HashMap<>();
        sqlMap.put(WIN, "INSERT INTO `rate`(`user_id`, `event_id`, `money`, `rate_type`, `event_member1_id`) " +
                "VALUES(?, ?, ?, ?, ?);");
        sqlMap.put(FIRST_GOAL, sqlMap.get(WIN));
        sqlMap.put(DRAW, "INSERT INTO `rate`(`user_id`, `event_id`, `money`, `rate_type`) " +
                "VALUES(?, ?, ?, ?);");
        sqlMap.put(EXACT_SCORE, "INSERT INTO `rate`(`user_id`, `event_id`, `money`, `rate_type`, " +
                "`event_member1_id`, `event_member1_score`, `event_member2_id`, `event_member2_score`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            try {
                statement = connection.prepareStatement(sqlMap.get(rate.getType()));
                statement.setInt(1, rate.getUserId());
                statement.setInt(2, rate.getEventId());
                statement.setBigDecimal(3, rate.getSum());
                statement.setString(4, rate.getType());
                if((rate.getType().equals(WIN)) || (rate.getType().equals(FIRST_GOAL))){
                    statement.setInt(5, rate.getMember1Id());
                }
                if(rate.getType().equals(EXACT_SCORE)){
                    statement.setInt(6, rate.getMember1Score());
                    statement.setInt(7, rate.getMember2Id());
                    statement.setInt(8, rate.getMember2Score());

                }
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
        return rate;
    }
}
