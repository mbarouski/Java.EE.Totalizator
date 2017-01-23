package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Rate;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.RateService;
import sport.totalizator.service.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import static sport.totalizator.entity.Rate.EXACT_SCORE;
import static sport.totalizator.entity.Rate.WIN;

public class RateServiceImpl implements RateService {
    private static final RateServiceImpl instance = new RateServiceImpl();
    private static final Logger log = Logger.getLogger(RateServiceImpl.class);
    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private UserDAO userDAO;
    private RateDAO rateDAO;

    public static RateServiceImpl getInstance(){
        return instance;
    }

    RateServiceImpl(){
        rateDAO = DAOFactory.getFactory().getRateDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

    private int checkInt(String stringValue, ExceptionWithErrorList rateException, String errorMessage){
        int intValue;
        try{
            intValue = Integer.parseInt(stringValue);
            return intValue;
        } catch (NumberFormatException exc){
            rateException.addMessage(errorMessage);
            return 0;
        }
    }

    @Override
    public Rate makeRate(String type, String eventId, String username, String money,
                         String member1Id, String member1Score, String member2Id, String member2Score)
            throws ServiceException, ExceptionWithErrorList {
        Rate rate = new Rate();
        ExceptionWithErrorList rateException = new ExceptionWithErrorList(rate);
        if((type == null) || (type.isEmpty())){
            rateException.addMessage("err.rate-type-is-invalid");
        }
        rate.setType(type);
        rate.setEventId(checkInt(eventId, rateException, "err.event-id-is-invalid"));
        if(type.equals(WIN)){
            rate.setMember1Id(checkInt(member1Id, rateException, "err.member-id-is-invalid"));
        }
        if(type.equals(EXACT_SCORE)){
            rate.setMember1Id(checkInt(member1Id, rateException, "err.member-id-is-invalid"));
            rate.setMember2Id(checkInt(member2Id, rateException, "err.member-id-is-invalid"));
            rate.setMember1Score(checkInt(member1Score, rateException, "err.member-score-is-invalid"));
            rate.setMember2Score(checkInt(member2Score, rateException, "err.member-score-is-invalid"));
        }
        BigDecimal bigDecimalmoney;
        try{
            bigDecimalmoney = BigDecimal.valueOf(Double.parseDouble(money));
            rate.setSum(bigDecimalmoney);
        } catch (NumberFormatException exc){
            rateException.addMessage("err.amount-is-invalid");
        }
        if(rateException.getErrorMessageList().size() != 0){
            throw rateException;
        }
        Connection connection = null;
        Savepoint savepoint = null;
        try{
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();
            rate.setUserId(userDAO.getUserIdByLogin(username));
            if(!userDAO.haveMoney(rate.getUserId(), rate.getSum())){
                rateException.addMessage("err.not-enough-money");
                throw rateException;
            }
            userDAO.withdrawMoneyFromUser(connection, rate.getUserId(), rate.getSum());
            rateDAO.addRate(connection, rate);
            connection.commit();
        } catch (DAOException | SQLException exc){
            try{
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
        return rate;
    }
}
