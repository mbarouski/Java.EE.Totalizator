package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.EventResult;

public interface EventResultDAO {
    EventResult addEventResult(EventResult eventResult) throws DAOException;

    EventResult getEventResultByEvent(int eventId) throws DAOException;
}
