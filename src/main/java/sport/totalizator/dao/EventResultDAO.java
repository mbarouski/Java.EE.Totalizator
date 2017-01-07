package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.EventResult;

public interface EventResultDAO {
    EventResult addEventReslt(EventResult eventResult) throws DAOException;
}
