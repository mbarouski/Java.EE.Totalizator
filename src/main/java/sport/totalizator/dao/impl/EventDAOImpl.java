package sport.totalizator.dao.impl;


import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.db.jdbc.ConnectionPoolException;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO{
    private static final String SQL_FOR_FINISH_EVENT = "UPDATE `event` " +
            "SET `event_status` = 'FINISHED' " +
            "WHERE `event_id` = ?;";
    private static final String SQL_FOR_GET_ALL_NOT_ENDED_EVENTS_SORTED_BY_DATE = "SELECT `event`.`event_id` " +
            "AS `id`, `event_name`, `league_name`,  `event_status`, " +
            "`event_start_date` AS `date` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE `event_status` = 'POSTED' " +
            "AND `event_start_date` > now() " +
            "ORDER BY `date`;";
    private static final String SQL_FOR_GET_ALL_NOT_ENDED_EVENTS_BY_CATEGORY_ID = "SELECT `event`.`event_id` AS `id`, " +
            "`event_name`, `event_category_id`, `league_name`, `event_status`, " +
            "`event_start_date` AS `date` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE `event_status` = 'POSTED' " +
            "AND `event_start_date` > now()" +
            "AND `event_category_id` = ?;";
    private static final String SQL_FOR_GET_ALL_ENDED_EVENTS = "SELECT `event`.`event_id` " +
            "AS `id`, `event_name`, `event_category_id`, `league_name`, " +
            "`event_status`, `event_start_date` AS `date` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE `event_status` = 'FINISHED' " +
            "OR `event_start_date` < now();";
    private static final String SQL_FOR_SEARCH_EVENTS = "SELECT `event`.`event_id` " +
            "AS `id`, `event_name`, `event_category_id`, `league_name`, " +
            "`event_status`, `event_start_date` AS `date` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE MATCH(`event_name`) " +
            "AGAINST (? IN BOOLEAN MODE);";
    private static final String SQL_GOR_GET_ALL_NOT_ENDED_EVENTS = "SELECT `event`.`event_id` AS `id`, `event_name`, `league_name`, " +
            "`event_status`, `event_start_date` AS `date` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE `event_status` = 'POSTED' " +
            "AND `event_start_date` > now();";
    private static final String SQL_FOR_GET_EVENT_BY_ID = "SELECT `event_id` AS `id`, `event_name`, `event_status`, " +
            "`live_translation_reference` AS `link`, `event_start_date` AS `date`, `league_name` " +
            "FROM `event` " +
            "LEFT JOIN `league` " +
            "ON `event`.`league_id` = `league`.`league_id` " +
            "WHERE `event_id` = ?;";
    private static final String SQL_FOR_ADD_EVENT = "INSERT INTO `event`(`event_name`, `league_id`, " +
            "`live_translation_reference`, `event_start_date`) " +
            "VALUES (?, ?, ?, ?);";

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
        } catch (SQLException | ConnectionPoolException exc){
            log.error(exc);
            throw new DAOException(exc.getMessage());
        } finally {
            pool.returnConnectionToPool(connection);
        }
        return result;
    }

    public List<Event> getAllNotEndedEventsByCategoryId(int categoryId) throws DAOException {
        return getEventsBySql(SQL_FOR_GET_ALL_NOT_ENDED_EVENTS_BY_CATEGORY_ID, categoryId);
    }

    @Override
    public List<Event> searchEvents(String searchQuery) throws DAOException {
        return getEventsBySql(SQL_FOR_SEARCH_EVENTS, wrapSearchQuery(searchQuery));
    }

    private String wrapSearchQuery(String searchQuery){
        searchQuery = searchQuery.replaceAll(" +", " ").trim();
        searchQuery = searchQuery.replaceAll("[^a-zA-Z0-9а-яА-Я]+", " ");
        StringBuilder sb = new StringBuilder();
        for(String str : searchQuery.split("[^A-Za-zа-яА-Я0-9]")){
            sb.append("*");
            sb.append(str);
            sb.append("*");
        }
        return sb.toString();
    }

    public List<Event> getAllEndedEvents() throws DAOException {
        return getEventsBySql(SQL_FOR_GET_ALL_ENDED_EVENTS);
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
        return getEventsBySql(SQL_GOR_GET_ALL_NOT_ENDED_EVENTS);
    }

    @Override
    public List<Event> getAllNotEndedEventsSortedByDate() throws DAOException {
        return getEventsBySql(SQL_FOR_GET_ALL_NOT_ENDED_EVENTS_SORTED_BY_DATE);
    }

    @Override
    public Event getEventById(int eventId) throws DAOException{
        Event event = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(SQL_FOR_GET_EVENT_BY_ID);
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
        } catch (SQLException | ConnectionPoolException exc){
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
    public Event addEvent(Connection connection, Event event) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_FOR_ADD_EVENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, event.getEventName());
            statement.setInt(2, event.getLeagueId());
            statement.setString(3, event.getLiveTranslationLink());
            statement.setTimestamp(4, new Timestamp(event.getEventDate().getTime()));
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
                try {
                    statement.close();
                } catch (SQLException exc){
                    log.error(exc);
                }
            }
        }
        return event;
    }

    @Override
    public void finishEvent(Connection connection, int eventId) throws DAOException {
        PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(SQL_FOR_FINISH_EVENT);
                statement.setInt(1, eventId);
                statement.executeUpdate();
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    try {
                        statement.close();
                    } catch (SQLException exc){
                        log.error(exc);
                    }
                }
            }
    }
}
