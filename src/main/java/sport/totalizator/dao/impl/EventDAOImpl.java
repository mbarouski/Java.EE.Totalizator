package sport.totalizator.dao.impl;


import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<Event> getAllEvents(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT `event`.`event_id` AS `id`, `event_name`, `league_name`, `event_start_date` AS `date`, count(`rate`.`rate_id`) AS `rate_count` " +
                "FROM `event` " +
                "JOIN `league` " +
                "ON `event`.`league_id` = `league`.`league_id` " +
                "JOIN `rate` " +
                "ON `rate`.`event_id` = `event`.`event_id` " +
                "GROUP BY `rate`.`event_id`;";
        List<Event> result = new ArrayList<Event>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            resultSet = statement.getResultSet();
            Event event;
            while(resultSet.next()){
                event = new Event();
                event.setEventName(resultSet.getString("event_name"));
                event.setEventDate(resultSet.getDate("date"));
                event.setEventTime(resultSet.getTime("date"));
                event.setEventId(resultSet.getInt("id"));
                event.setRateCount(resultSet.getInt("rate_count"));
                event.setEventLeague(resultSet.getString("league_name"));
                result.add(event);
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
