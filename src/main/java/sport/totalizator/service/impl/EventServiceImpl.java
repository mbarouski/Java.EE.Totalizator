package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Event;
import sport.totalizator.exception.EventException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.DateParser;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService {
    private static final EventServiceImpl instance = new EventServiceImpl();
    private static final Logger log = Logger.getLogger(EventServiceImpl.class);

    private EventDAO eventDAO;
    private MemberDAO memberDAO;

    public static EventServiceImpl getInstance(){
        return instance;
    }

    EventServiceImpl(){
        eventDAO = DAOFactory.getFactory().getEventDAO();
        memberDAO = DAOFactory.getFactory().getMemberDAO();
    }

    @Override
    public List<Event> getAllNotEndedEvents() throws ServiceException {
        try {
            return eventDAO.getAllNotEndedEvents();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getAllNotEndedEventsSortedByDate() throws ServiceException {
        try {
            return eventDAO.getAllNotEndedEventsSortedByDate();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getAllNotEndedEventsByCategoryId(String categoryId) throws ServiceException {
        try {
            int intLeagueId = Integer.parseInt(categoryId);
            return eventDAO.getAllNotEndedEventsByCategoryId(intLeagueId);
        } catch (DAOException | NumberFormatException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Event> getAllEndedEvents() throws ServiceException {
        try {
            return eventDAO.getAllEndedEvents();
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public Event getEventById(int eventId) throws ServiceException{
        try{
            Event event = eventDAO.getEventById(eventId);
            event.setMembers(memberDAO.getMembersByEvent(event.getEventId()));
            return event;
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public Event addEvent(String name, String leagueId, String rateTypes, String liveTranslationLink,
                          String date, List<Integer> memberIds)
            throws ServiceException, EventException{
        try {
            Event event = new Event();
            EventException eventException = new EventException(event);
            if(name.isEmpty() || (name == null)){
                eventException.addErrorMessage("err.event-empty-name");
            }
            event.setEventName(name);
            int intLeagueId;
            try {
                intLeagueId = Integer.parseInt(leagueId);
            }
            catch (NumberFormatException exc){
                log.error(exc);
                intLeagueId = 0;
            }
            event.setLeagueId(intLeagueId);
            Date sqlDate = DateParser.parse(date);
            if(sqlDate.before(Date.valueOf(LocalDate.now()))){
                eventException.addErrorMessage("err.event-old-date");
            }
            event.setEventDate(sqlDate);
            event.setLiveTranslationLink(liveTranslationLink);
            event.setRateTypes(rateTypes);
            if(!eventException.getErrorMessageList().isEmpty()){
                throw eventException;
            }
            event =  eventDAO.addEvent(event);
            memberDAO.attachMembersToEvent(memberIds, event.getEventId());
            return event;
        } catch (DAOException | ParseException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }
}
