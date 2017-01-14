package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.EventResult;

import java.sql.*;

public class EventResultDAOImpl implements EventResultDAO {
    private static final String SQL_FOR_GET_EVENT_RESULT_BY_EVENT = "SELECT `event_id`, `winner_id`, `loser_id`, " +
            "`winner_score`, `loser_score` " +
            "FROM `eventresult` " +
            "WHERE `event_id` = ?;";
    private static final String SQL_FOR_ADD_EVENT_RESULT = "INSERT INTO `eventresult`(`event_id`, `winner_id`, " +
            "`winner_score`, `loser_id`, `loser_score`) " +
            "VALUES(?, ?, ?, ?, ?)";

    private static final Logger log = Logger.getLogger(EventResultDAOImpl.class);
    private static final EventResultDAOImpl instance = new EventResultDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static EventResultDAOImpl getInstance(){
        return instance;
    }

    EventResultDAOImpl(){}

    @Override
    public EventResult addEventResult(EventResult eventResult) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(SQL_FOR_ADD_EVENT_RESULT);
                statement.setInt(1, eventResult.getEventId());
                statement.setInt(2, eventResult.getWinnerId());
                statement.setInt(3, eventResult.getWinnerScore());
                statement.setInt(4, eventResult.getLoserId());
                statement.setInt(5, eventResult.getLoserScore());
                statement.executeUpdate();
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
        return eventResult;
    }

    @Override
    public EventResult getEventResultByEvent(int eventId) throws DAOException {
        EventResult eventResult = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(SQL_FOR_GET_EVENT_RESULT_BY_EVENT);
                statement.setInt(1, eventId);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    if(resultSet.next()){
                        eventResult = new EventResult();
                        eventResult.setEventId(resultSet.getInt("event_id"));
                        eventResult.setWinnerScore(resultSet.getInt("winner_score"));
                        eventResult.setLoserScore(resultSet.getInt("loser_score"));
                        eventResult.setWinnerId(resultSet.getInt("winner_id"));
                        eventResult.setLoserId(resultSet.getInt("loser_id"));
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
        return eventResult;
    }

}
