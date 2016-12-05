package sport.totalizator.service;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Event;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents() throws ServiceException;

    List<Event> getAllEventsSortedByRateCount() throws ServiceException;

    List<Event> getAllEventsSortedByDate() throws ServiceException;

    List<Event> getNotEndedEventsByCategoryId(int categoryId) throws ServiceException;

    List<Event> getEndedEvents() throws ServiceException;

    Event getEventById(int eventId) throws ServiceException;
}
