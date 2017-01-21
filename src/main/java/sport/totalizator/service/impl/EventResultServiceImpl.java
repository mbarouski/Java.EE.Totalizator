package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.EventResultDAO;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Event;
import sport.totalizator.entity.EventResult;
import sport.totalizator.entity.Rate;
import sport.totalizator.exception.EventResultException;
import sport.totalizator.service.EventResultService;
import sport.totalizator.service.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static sport.totalizator.entity.Rate.DRAW;
import static sport.totalizator.entity.Rate.EXACT_SCORE;
import static sport.totalizator.entity.Rate.WIN;

public class EventResultServiceImpl implements EventResultService {
    private static final EventResultServiceImpl instance = new EventResultServiceImpl();
    private static final Logger log = Logger.getLogger(EventResultServiceImpl.class);
    EventResultDAO eventResultDAO;
    EventDAO eventDAO;
    RateDAO rateDAO;
    UserDAO userDAO;
    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

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
        Connection connection = null;
        Savepoint savepoint = null;
        try{
            connection.setAutoCommit(false);
            connection.setSavepoint();
            eventResultDAO.addEventResult(connection, eventResult);
            eventDAO.finishEvent(connection, eventResult.getEventId());
            distributePrize(connection, savepoint, eventResult);
        } catch (DAOException | SQLException exc){
            try {
                if(savepoint != null) {
                    connection.rollback(savepoint);
                }
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
                connectionPool.returnConnectionToPool(connection);
            }
        }
        return eventResult;
    }

    private void distributePrize(Connection connection, Savepoint savepoint, EventResult eventResult){
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BigDecimal fullMoneyAmount = rateDAO.getFullMoneyAmountForEvent(eventResult.getEventId());
                    List<Rate> rateList = rateDAO.getRatesForEvent(eventResult.getEventId());
                    List<Rate> winRateList = new ArrayList<Rate>();
                    for (Rate rate : rateList) {
                        if(checkWin(rate, eventResult)){
                            winRateList.add(rate);
                        }
                    }
                    BigDecimal moneyPerPerson = fullMoneyAmount.divide(BigDecimal.valueOf(winRateList.size()), 2, BigDecimal.ROUND_FLOOR);
                    for(Rate rate : rateList){
                        rate.setWin(BigDecimal.valueOf(0.0));
                    }
                    for(Rate rate : winRateList){
                        rate.setWin(moneyPerPerson);
                    }
                    for(Rate rate : rateList){
                        rateDAO.setWinForRate(connection, rate);
                        userDAO.fillUpBalanceForUser(connection, rate.getUserId(), rate.getWin());
                    }
                    connection.commit();
                } catch (DAOException | SQLException exc){
                    try {
                        connection.rollback(savepoint);
                    } catch (SQLException sqlExc){
                        log.error(sqlExc);
                    }
                }
            }
        })).run();
    }

    private boolean checkWin(Rate rate, EventResult eventResult){
        switch (rate.getType()){
            case WIN:
                if(eventResult.getWinnerId() == rate.getMember1Id()){
                    return true;
                }
                return false;
            case DRAW:
                if(eventResult.getLoserScore() == eventResult.getWinnerScore()){
                    return true;
                }
                return false;
            case EXACT_SCORE:
                if(eventResult.getWinnerId() == rate.getMember1Id()){
                    if((eventResult.getWinnerScore() == rate.getMember1Score()) &&
                            (eventResult.getLoserScore() == rate.getMember2Score())){
                        return true;
                    }
                    return false;
                } else{
                    if((eventResult.getWinnerScore() == rate.getMember2Score()) &&
                            (eventResult.getLoserScore() == rate.getMember1Score())){
                        return true;
                    }
                    return false;
                }
        }
        return false;
    }
}
