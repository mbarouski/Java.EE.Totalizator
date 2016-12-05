package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Event;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public class EventServiceImpl implements EventService {
    private static final EventServiceImpl instance = new EventServiceImpl();
    private static final Logger log = Logger.getLogger(EventServiceImpl.class);

    private EventDAO eventDAO;

    public static EventServiceImpl getInstance(){
        return instance;
    }

    EventServiceImpl(){
        eventDAO = DAOFactory.getFactory().getEventDAO();
    }

    @Override
    public List<Event> getAllEvents() throws ServiceException {
        try {
            return eventDAO.getAllEvents();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getAllEventsSortedByRateCount() throws ServiceException {
        try {
            return eventDAO.getAllEventsSortedByRateCount();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getAllEventsSortedByDate() throws ServiceException {
        try {
            return eventDAO.getAllEventsSortedByDate();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getNotEndedEventsByCategoryId(int categoryId) throws ServiceException {
        try {
            return eventDAO.getNotEndedEventsByCategoryId(categoryId);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getEndedEvents() throws ServiceException {
        try {
            return eventDAO.getEndedEvents();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public Event getEventById(int eventId) throws ServiceException{
        try{
            return eventDAO.getEventById(eventId);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }
}
