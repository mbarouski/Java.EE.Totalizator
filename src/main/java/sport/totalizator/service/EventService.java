package sport.totalizator.service;

import sport.totalizator.entity.Category;
import sport.totalizator.entity.Event;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.PaginationObject;

import java.util.List;

/**
 * {@link EventService} provides methods for interaction of controller and DAO layer from side of event.
 */
public interface EventService {
    /**
     * Method returns all not finished {@link Event}s wrapped in {@link PaginationObject}.
     * @param page
     * @return
     * @throws ServiceException
     */
    PaginationObject<Event> getAllNotEndedEvents(int page) throws ServiceException;

    /**
     * Method returns all not finished {@link Event}s sorted by date wrapped in {@link PaginationObject}.
     * @param page
     * @return
     * @throws ServiceException
     */
    PaginationObject<Event> getAllNotEndedEventsSortedByDate(int page) throws ServiceException;

    /**
     * Method returns all not finished {@link Event}s that belong to {@link Category} wrapped in {@link PaginationObject}.
     * @param categoryId
     * @param page
     * @return
     * @throws ServiceException
     */
    PaginationObject<Event> getAllNotEndedEventsByCategoryId(String categoryId, int page) throws ServiceException;

    /**
     * Method that returns list of all {@link Event}s that are suitable for search query wrapped in {@link PaginationObject}.
     * @param searchQuery
     * @param page
     * @return
     * @throws ServiceException
     */
    PaginationObject<Event> searchEvents(String searchQuery, int page) throws ServiceException;

    /**
     * Method returns all finished {@link Event}s wrapped in {@link PaginationObject}.
     * @param page
     * @return
     * @throws ServiceException
     */
    PaginationObject<Event> getAllEndedEvents(int page) throws ServiceException;

    /**
     * Method return {@link Event} by his id.
     * @param eventId
     * @return
     * @throws ServiceException
     */
    Event getEventById(int eventId) throws ServiceException;

    /**
     * Method checks data for {@link Event} adding and manages this process.
     * @param name
     * @param leagueId
     * @param liveTranslationLink
     * @param date
     * @param memberIds
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Event addEvent(String name, String leagueId, String liveTranslationLink,
                          String date, List<Integer> memberIds)
            throws ServiceException, ExceptionWithErrorList;
}
