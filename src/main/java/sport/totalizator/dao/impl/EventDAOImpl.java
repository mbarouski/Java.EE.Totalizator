package sport.totalizator.dao.impl;


import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO{
    private static final Logger log = Logger.getLogger(EventDAOImpl.class);
    private static final EventDAOImpl instance = new EventDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static EventDAOImpl getInstance(){
        return instance;
    }

    EventDAOImpl(){}

    private List<Event> getEventsBySql(String sql, Object... params) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Event> result = new ArrayList<Event>();

        try {
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(sql);
                insertParamsIntoPreparedStatement(statement, params);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    Event event;
                    while (resultSet.next()) {
                        event = new Event();
                        event.setEventName(resultSet.getString("event_name"));
                        event.setEventDate(resultSet.getDate("date"));
                        event.setEventTime(resultSet.getTime("date"));
                        event.setEventId(resultSet.getInt("id"));
                        event.setEventLeague(resultSet.getString("league_name"));
                        result.add(event);
                    }
                } catch (SQLException exc){
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

    public List<Event> getAllNotEndedEventsByCategoryId(int categoryId) throws DAOException {
        String sql = "SELECT `event`.`event_id` AS `id`, `event_name`, `event_category_id`, `league_name`, `event_status`, " +
                "`event_start_date` AS `date` " +
                "FROM `event` " +
                "LEFT JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "WHERE `event_status` = 'POSTED' " +
                "AND `event_start_date` > now()" +
                "AND `event_category_id` = ?;";
        return getEventsBySql(sql, categoryId);
    }

    public List<Event> getAllEndedEvents() throws DAOException {
        String sql = "SELECT `event`.`event_id` AS `id`, `event_name`, `event_category_id`, `league_name`, " +
                "`event_status`, `event_start_date` AS `date` " +
                "FROM `event` " +
                "LEFT JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "WHERE `event_status` = 'FINISHED' " +
                "OR `event_start_date` < now();";
        return getEventsBySql(sql);
    }

    private void insertParamsIntoPreparedStatement(PreparedStatement statement, Object[] params) throws SQLException{
        Object param;
        for(int i = 0; i < params.length; i++){
            param = params[i];
            if(param.getClass() == Integer.class) {
                statement.setInt(i + 1, (Integer) param);
            }
            else if (param.getClass() == String.class){
                statement.setString(i + 1, (String) param);
            }
        }
    }

    @Override
    public List<Event> getAllNotEndedEvents() throws DAOException {
        String sql = "SELECT `event`.`event_id` AS `id`, `event_name`, `league_name`, " +
                "`event_status`, `event_start_date` AS `date` " +
                "FROM `event` " +
                "LEFT JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "WHERE `event_status` = 'POSTED' " +
                "AND `event_start_date` > now();";
        return getEventsBySql(sql);
    }

    @Override
    public List<Event> getAllNotEndedEventsSortedByDate() throws DAOException {
        String sql = "SELECT `event`.`event_id` AS `id`, `event_name`, `league_name`,  `event_status`, " +
                "`event_start_date` AS `date` " +
                "FROM `event` " +
                "LEFT JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "WHERE `event_status` = 'POSTED' " +
                "AND `event_start_date` > now() " +
                "ORDER BY `date`;";
        return getEventsBySql(sql);
    }

    @Override
    public Event getEventById(int eventId) throws DAOException{
        String sql = "SELECT `event_id` AS `id`, `event_name`, `rate_types`, `event_status`, " +
                "`live_translation_reference` AS `link`, `event_start_date` AS `date`, `league_name` " +
                "FROM `event` " +
                "LEFT JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "WHERE `event_id` = ?;";
        Event event = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, eventId);
                statement.execute();
                try {
                    resultSet = statement.getResultSet();
                    if(resultSet.next()){
                        event = new Event();
                        event.setEventName(resultSet.getString("event_name"));
                        event.setEventLeague(resultSet.getString("league_name"));
                        event.setEventId(resultSet.getInt("id"));
                        event.setEventDate(resultSet.getDate("date"));
                        event.setEventTime(resultSet.getTime("date"));
                        event.setLiveTranslationLink(resultSet.getString("link"));
                        event.setStatus(resultSet.getString("event_status"));
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
        return event;
    }

    @Override
    public Event addEvent(Event event) throws DAOException {
        String sql = "INSERT INTO `event`(`event_name`, `league_id`, `rate_types`, `live_translation_reference`, `event_start_date`) " +
                "VALUES (?, ?, ?, ?, ?);";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, event.getEventName());
                statement.setInt(2, event.getLeagueId());
                statement.setString(3, event.getRateTypes());
                statement.setString(4, event.getLiveTranslationLink());
                statement.setTimestamp(5, new Timestamp(event.getEventDate().getTime()));
                int result = statement.executeUpdate();
                try{
                    resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    event.setEventId(resultSet.getInt(1));
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
        return event;
    }
}
