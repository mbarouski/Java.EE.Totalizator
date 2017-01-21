package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.EventResult;

import java.sql.Connection;
import java.sql.SQLException;

public interface EventResultDAO {
    EventResult addEventResult(Connection connection, EventResult eventResult) throws DAOException;

    EventResult getEventResultByEvent(int eventId) throws DAOException;
}
