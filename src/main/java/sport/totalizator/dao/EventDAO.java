package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;

import java.util.List;

public interface EventDAO {
    public List<Event> getAllEvents() throws DAOException;

    public List<Event> getAllEventsSortedByRateCount() throws DAOException;

    public List<Event> getAllEventsSortedByDate() throws DAOException;
}
