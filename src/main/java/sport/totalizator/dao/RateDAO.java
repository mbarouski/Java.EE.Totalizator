package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Rate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * RateDAO is interface that defines methods for interaction of service layer with database from side of {@link Rate}.
 */
public interface RateDAO {
    /**
     * Method returns list of active {@link Rate}'s for user.
     * @param userId
     * @return
     * @throws DAOException
     */
    List<Rate> getActiveRatesForUser(int userId) throws DAOException;

    /**
     * Method returns list of finished {@link Rate}'s for user.
     * @param userId
     * @return
     * @throws DAOException
     */
    List<Rate> getFinishedRatesForUser(int userId) throws DAOException;

    /**
     * Method adds {@link Rate} to database.
     * @param connection
     * @param rate
     * @return
     * @throws DAOException
     */
    Rate addRate(Connection connection, Rate rate) throws DAOException;

    /**
     * Method calculates sum of {@link Rate}s for {@link sport.totalizator.entity.Event}.
     * @param eventId
     * @return
     * @throws DAOException
     */
    BigDecimal getFullMoneyAmountForEvent(int eventId) throws DAOException;

    /**
     * Method returns list of {@link Rate}s for {@link sport.totalizator.entity.Event}.
     * @param eventId
     * @return
     * @throws DAOException
     */
    List<Rate> getRatesForEvent(int eventId) throws DAOException;

    /**
     * Method changes win money field in {@link Rate}.
     * @param connection
     * @param rate
     * @throws DAOException
     */
    void setWinForRate(Connection connection, Rate rate) throws DAOException;
}
