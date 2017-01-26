package sport.totalizator.service;

import sport.totalizator.db.jdbc.ConnectionPoolException;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

/**
 * {@link PaySystemService} provides methods for interaction of controller and DAO layer from side of payment system.
 */
public interface PaySystemService {
    /**
     * Method checks data for balance filling up and manages this process.
     * @param username
     * @param cardNumber
     * @param validityDate
     * @param cardCode
     * @param amount
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Operation fillUpBalance(String username, String cardNumber, String validityDate, String cardCode, String amount)
            throws ServiceException, ExceptionWithErrorList, ConnectionPoolException;

    /**
     * Method checks data for money withdrawing from balance and manages this process.
     * @param username
     * @param cardNumber
     * @param validityDate
     * @param amount
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Operation withdrawMoney(String username, String cardNumber, String validityDate, String amount)
            throws ServiceException, ExceptionWithErrorList, ConnectionPoolException;
}
