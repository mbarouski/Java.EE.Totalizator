package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Rate;

import java.math.BigDecimal;
import java.util.List;

public interface RateDAO {
    List<Rate> getActiveRatesForUser(int userId) throws DAOException;

    List<Rate> getFinishedRatesForUser(int userId) throws DAOException;

    Rate addRate(Rate rate) throws DAOException;

    BigDecimal getFullMoneyAmountForEvent(int eventId) throws DAOException;

    List<Rate> getRatesForEvent(int eventId) throws DAOException;

    void setWinForRate(Rate rate) throws DAOException;
}
