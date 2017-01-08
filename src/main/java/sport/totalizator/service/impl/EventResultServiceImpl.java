package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.EventResultException;
import sport.totalizator.service.EventResultService;
import sport.totalizator.service.exception.ServiceException;

public class EventResultServiceImpl implements EventResultService {
    private static final EventResultServiceImpl instance = new EventResultServiceImpl();
    private static final Logger log = Logger.getLogger(EventResultServiceImpl.class);
    EventResultDAO eventResultDAO;
    EventDAO eventDAO;
    RateDAO rateDAO;
    UserDAO userDAO;

    public static EventResultService getInstance(){
        return instance;
    }

    EventResultServiceImpl(){
        eventResultDAO = DAOFactory.getFactory().getEventResultDAO();
        eventDAO = DAOFactory.getFactory().getEventDAO();
        rateDAO = DAOFactory.getFactory().getRateDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

    private int checkInt(String stringValue, EventResultException eventResultException, String errorMessage){
        int intValue;
        try{
            intValue = Integer.parseInt(stringValue);
            return intValue;
        } catch (NumberFormatException exc){
            eventResultException.addErrorMessage(errorMessage);
            return 0;
        }
    }

    @Override
    public EventResult addEventResult(String eventId, String winnerId, String loserId, String winnerScore, String loserScore) throws ServiceException, EventResultException {
        EventResult eventResult = new EventResult();
        EventResultException eventResultException = new EventResultException(eventResult);
        eventResult.setEventId(checkInt(eventId, eventResultException, "err.event-id-is-invalid"));
        eventResult.setWinnerId(checkInt(winnerId, eventResultException, "err.winner-id-is-invalid"));
        eventResult.setWinnerScore(checkInt(winnerScore, eventResultException, "err.winner-score-is-invalid"));
        eventResult.setLoserId(checkInt(loserId, eventResultException, "err.loser-id-is-invalid"));
        eventResult.setLoserScore(checkInt(loserScore, eventResultException, "err.loser-score-is-invalid"));
        if(eventResultException.getErrorMessageList().size() > 0){
            throw eventResultException;
        }
        try{
            eventResultDAO.addEventResult(eventResult);
            eventDAO.finishEvent(eventResult.getEventId());

        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return eventResult;
    }
}
