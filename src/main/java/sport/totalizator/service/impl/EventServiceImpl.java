package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.EventService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.util.DateParser;
import sport.totalizator.util.PaginationObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService {
    private static final EventServiceImpl instance = new EventServiceImpl();
    private static final Logger log = Logger.getLogger(EventServiceImpl.class);
    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

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
    public PaginationObject<Event> getAllNotEndedEventsSortedByDate(int page) throws ServiceException {
        try {
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllNotEndedEventsSortedByDate();
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
    public PaginationObject<Event> getAllNotEndedEventsByCategoryId(String categoryId, int page) throws ServiceException {
        try {
            int intLeagueId = Integer.parseInt(categoryId);
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.getAllNotEndedEventsByCategoryId(intLeagueId);
            paginationObject.setPageCount((int)Math.ceil((double)events.size() / PaginationObject.PER_PAGE));
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
    public PaginationObject<Event> searchEvents(String searchQuery, int page) throws ServiceException {
        try {
            PaginationObject<Event> paginationObject = new PaginationObject<>();
            List<Event> events = eventDAO.searchEvents(searchQuery);
            paginationObject.setPageCount((int)Math.ceil((double)events.size() / PaginationObject.PER_PAGE));
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
            paginationObject.setPage(page);
            paginationObject.setPageCount((int)Math.ceil((double)events.size() / PaginationObject.PER_PAGE));
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
            throws ServiceException, ExceptionWithErrorList {
        Connection connection = null;
        Savepoint savepoint = null;
        try {
            Event event = new Event();
            ExceptionWithErrorList eventException = new ExceptionWithErrorList(event);
            if(name.isEmpty() || (name == null)){
                eventException.addMessage("err.event-empty-name");
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
                eventException.addMessage("err.event-old-date");
            }
            event.setEventDate(sqlDate);
            event.setLiveTranslationLink(liveTranslationLink);
            event.setRateTypes(rateTypes);
            if(!eventException.getErrorMessageList().isEmpty()){
                throw eventException;
            }
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();
            event =  eventDAO.addEvent(connection, event);
            memberDAO.attachMembersToEvent(connection, memberIds, event.getEventId());
            connection.commit();
            return event;
        } catch (DAOException | ParseException | SQLException exc){
            try {
                connection.rollback(savepoint);
            } catch (SQLException sqlExc){
                log.error(sqlExc);
            }
            log.error(exc);
            throw new ServiceException(exc);
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException exc){
                    log.error(exc);
                }
            }
        }
    }
}
