package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.RateDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.entity.Rate;
import sport.totalizator.exception.RateException;
import sport.totalizator.service.RateService;
import sport.totalizator.service.exception.ServiceException;

import java.math.BigDecimal;

import static sport.totalizator.entity.Rate.EXACT_SCORE;
import static sport.totalizator.entity.Rate.FIRST_GOAL;
import static sport.totalizator.entity.Rate.WIN;

public class RateServiceImpl implements RateService {
    private static final RateServiceImpl instance = new RateServiceImpl();
    private static final Logger log = Logger.getLogger(RateServiceImpl.class);

    private UserDAO userDAO;
    private RateDAO rateDAO;

    public static RateServiceImpl getInstance(){
        return instance;
    }

    RateServiceImpl(){
        rateDAO = DAOFactory.getFactory().getRateDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

    private int checkInt(String stringValue, RateException rateException, String errorMessage){
        int intValue;
        try{
            intValue = Integer.parseInt(stringValue);
            return intValue;
        } catch (NumberFormatException exc){
            rateException.addErrorMessage(errorMessage);
            return 0;
        }
    }

    @Override
    public Rate makeRate(String type, String eventId, String username, String money, String member1Id, String member1Score, String member2Id, String member2Score) throws ServiceException, RateException {
        Rate rate = new Rate();
        RateException rateException = new RateException(rate);
        if((type == null) || (type.isEmpty())){
            rateException.addErrorMessage("err.rate-type-is-invalid");
        }
        rate.setType(type);
        rate.setEventId(checkInt(eventId, rateException, "err.event-id-is-invalid"));
        if(type.equals(FIRST_GOAL) || type.equals(WIN)){
            rate.setMember1Id(checkInt(member1Id, rateException, "err.member-id-is-invalid"));
        }
        if(type.equals(EXACT_SCORE)){
            rate.setMember2Id(checkInt(member2Id, rateException, "err.member-id-is-invalid"));
            rate.setMember1Score(checkInt(member1Score, rateException, "err.member-score-is-invalid"));
            rate.setMember2Score(checkInt(member2Score, rateException, "err.member-score-is-invalid"));
        }
        BigDecimal bigDecimalmoney;
        try{
            bigDecimalmoney = BigDecimal.valueOf(Double.parseDouble(money));
            rate.setSum(bigDecimalmoney);
        } catch (NumberFormatException exc){
            rateException.addErrorMessage("err.amount-is-invalid");
        }
        if(rateException.getErrorMessageList().size() != 0){
            throw rateException;
        }
        try{
            rate.setUserId(userDAO.getUserIdByLogin(username));
            if(!userDAO.haveMoney(rate.getUserId(), rate.getSum())){
                rateException.addErrorMessage("err.not-enough-money");
                throw rateException;
            }
            userDAO.withdrawMoneyFromUser(rate.getUserId(), rate.getSum());
            rateDAO.addRate(rate);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return rate;
    }
}
