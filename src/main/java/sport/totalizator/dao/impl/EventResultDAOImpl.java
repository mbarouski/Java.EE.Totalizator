package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.EventResult;

public class EventResultDAOImpl implements EventResultDAO {
    private static final Logger log = Logger.getLogger(EventResultDAOImpl.class);
    private static final EventResultDAOImpl instance = new EventResultDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static EventResultDAOImpl getInstance(){
        return instance;
    }

    EventResultDAOImpl(){}

    @Override
    public EventResult addEventReslt(EventResult eventResult) throws DAOException {
        return eventResult;
    }
}
