package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.EventResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * EventResultDAO is interface that defines methods for interaction of service layer with database from side of {@link EventResult}.
 */
public interface EventResultDAO {
    /**
     * Method adds {@link EventResult} to database.
     * @param connection
     * @param eventResult
     * @return
     * @throws DAOException
     */
    EventResult addEventResult(Connection connection, EventResult eventResult) throws DAOException;

    /**
     * Method returns {@link EventResult} of {@link sport.totalizator.entity.Event}.
     * @param eventId
     * @return
     * @throws DAOException
     */
    EventResult getEventResultByEvent(int eventId) throws DAOException;
}
