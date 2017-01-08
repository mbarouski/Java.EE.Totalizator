package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;

import java.util.List;

public interface EventDAO {
    List<Event> getAllNotEndedEvents() throws DAOException;

    List<Event> getAllNotEndedEventsSortedByDate() throws DAOException;

    List<Event> getAllNotEndedEventsByCategoryId(int categoryId) throws DAOException;

    List<Event> getAllEndedEvents() throws DAOException;

    Event getEventById(int eventId) throws DAOException;

    Event addEvent(Event event) throws DAOException;

    void finishEvent(int eventId) throws DAOException;

}
