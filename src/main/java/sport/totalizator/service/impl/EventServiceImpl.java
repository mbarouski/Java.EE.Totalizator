package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.EventException;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.DateParser;
import sport.totalizator.util.PaginationObject;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import static sport.totalizator.util.PaginationObject.DEFAULT_PAGE;

public class EventServiceImpl implements EventService {
    private static final EventServiceImpl instance = new EventServiceImpl();
    private static final Logger log = Logger.getLogger(EventServiceImpl.class);

    private EventDAO eventDAO;
    private MemberDAO memberDAO;
    private EventResultDAO eventResultDAO;

    public static EventServiceImpl getInstance(){
        return instance;
    }

    EventServiceImpl(){
        eventDAO = DAOFactory.getFactory().getEventDAO();
        memberDAO = DAOFactory.getFactory().getMemberDAO();
        eventResultDAO = DAOFactory.getFactory().getEventResultDAO();
    }

    @Override
    public PaginationObject<Event> getAllNotEndedEvents(int page) throws ServiceException {
        try {
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllNotEndedEvents();
            paginationObject.setPageCount((int)Math.ceil(events.size() / PaginationObject.PER_PAGE));
            paginationObject.setPage(page);
            int start = (paginationObject.getPage()-1) * PaginationObject.PER_PAGE;
            int end = start + PaginationObject.PER_PAGE > events.size() ? events.size() : start + PaginationObject.PER_PAGE;
            paginationObject.setElementList(events.subList(start, end));
            return paginationObject;
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public PaginationObject<Event> getAllNotEndedEventsSortedByDate(int page) throws ServiceException {
        try {
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllNotEndedEventsSortedByDate();
            paginationObject.setPageCount((int)Math.ceil(events.size() / PaginationObject.PER_PAGE));
            paginationObject.setPage(page);
            int start = (paginationObject.getPage()-1) * PaginationObject.PER_PAGE;
            int end = start + PaginationObject.PER_PAGE > events.size() ? events.size() : start + PaginationObject.PER_PAGE;
            paginationObject.setElementList(events.subList(start, end));
            return paginationObject;
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public PaginationObject<Event> getAllNotEndedEventsByCategoryId(String categoryId, int page) throws ServiceException {
        try {
            int intLeagueId = Integer.parseInt(categoryId);
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllNotEndedEventsByCategoryId(intLeagueId);
            paginationObject.setPageCount((int)Math.ceil(events.size() / PaginationObject.PER_PAGE));
            paginationObject.setPage(page);
            int start = (paginationObject.getPage()-1) * PaginationObject.PER_PAGE;
            int end = start + PaginationObject.PER_PAGE > events.size() ? events.size() : start + PaginationObject.PER_PAGE;
            paginationObject.setElementList(events.subList(start, end));
            return paginationObject;
        } catch (DAOException | NumberFormatException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
    }

    @Override
    public PaginationObject<Event> getAllEndedEvents(int page) throws ServiceException {
        try {
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllEndedEvents();
            paginationObject.setPageCount((int)Math.ceil((double)events.size() / PaginationObject.PER_PAGE));
            paginationObject.setPage(page);
            int start = (paginationObject.getPage()-1) * PaginationObject.PER_PAGE;
            int end = start + PaginationObject.PER_PAGE > events.size() ? events.size() : start + PaginationObject.PER_PAGE;
            paginationObject.setElementList(events.subList(start, end));
            return paginationObject;
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
            EventResult eventResult = eventResultDAO.getEventResultByEvent(eventId);
            if(eventResult != null) {
                eventResult.setWinnerName(memberDAO.getMemberNameById(eventResult.getWinnerId()));
                eventResult.setLoserName(memberDAO.getMemberNameById(eventResult.getLoserId()));
            }
            event.setResult(eventResult);
            long eventTime = event.getEventTime().getTime() + event.getEventDate().getTime();
            long nowTime = new java.util.Date().getTime();
            if(!(event.getStatus().equals("FINISHED")) && (eventTime < nowTime)){
                event.setCanAddResult(true);
            }
            if((eventTime > nowTime) && !(event.getStatus().equals("FINISHED"))){
                event.setCanMakeRate(true);
            }
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
