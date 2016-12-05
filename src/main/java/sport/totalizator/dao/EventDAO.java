package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;

import java.util.List;

public interface EventDAO {
    List<Event> getAllEvents() throws DAOException;

    List<Event> getAllEventsSortedByRateCount() throws DAOException;

    List<Event> getAllEventsSortedByDate() throws DAOException;

    List<Event> getNotEndedEventsByCategoryId(int categoryId) throws DAOException;

    List<Event> getEndedEvents() throws DAOException;

    Event getEventById(int eventId) throws DAOException;

}
