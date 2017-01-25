package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;

import java.sql.Connection;
import java.util.List;

/**
 * EventDAO is interface that defines methods for interaction of service layer with database from side of {@link Event}.
 */
public interface EventDAO {
    /**
     * Method that returns list of all not finished {@link Event}s.
     * @return
     * @throws DAOException
     */
    List<Event> getAllNotEndedEvents() throws DAOException;

    /**
     * Method that returns list of all not finished {@link Event}s sorted by date.
     * @return
     * @throws DAOException
     */
    List<Event> getAllNotEndedEventsSortedByDate() throws DAOException;

    /**
     * Method that returns list of all not finished {@link Event}s which belong to {@link sport.totalizator.entity.Category}.
     * @param categoryId
     * @return
     * @throws DAOException
     */
    List<Event> getAllNotEndedEventsByCategoryId(int categoryId) throws DAOException;

    /**
     * Method that returns list of all {@link Event}s that are suitable for search query.
     * @param searchQuery
     * @return
     * @throws DAOException
     */
    List<Event> searchEvents(String searchQuery) throws DAOException;

    /**
     * Method that returns list of all finished {@link Event}s.
     * @return
     * @throws DAOException
     */
    List<Event> getAllEndedEvents() throws DAOException;

    /**
     * Method that returns {@link Event} instance by eventId.
     * @param eventId
     * @return
     * @throws DAOException
     */
    Event getEventById(int eventId) throws DAOException;

    /**
     * Method adds {@link Event} to database.
     * @param connection
     * @param event
     * @return
     * @throws DAOException
     */
    Event addEvent(Connection connection, Event event) throws DAOException;

    /**
     * Method changes status of {@link Event} of 'FINISHED'.
     * @param connection
     * @param eventId
     * @throws DAOException
     */
    void finishEvent(Connection connection, int eventId) throws DAOException;

}
