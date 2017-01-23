package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.OperationDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.dao.impl.OperationDAOImpl;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.PaySystemService;
import sport.totalizator.service.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class PaySystemServiceImpl implements PaySystemService {
    private static final PaySystemServiceImpl instance = new PaySystemServiceImpl();
    private static final Logger log = Logger.getLogger(PaySystemServiceImpl.class);
    ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private OperationDAO operationDAO;
    private UserDAO userDAO;

    public static PaySystemServiceImpl getInstance(){
        return instance;
    }

    PaySystemServiceImpl(){
        operationDAO = DAOFactory.getFactory().getOperationDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

    @Override
    public Operation fillUpBalance(String username, String cardNumber, String validityDate, String cardCode, String amount)
            throws ServiceException, ExceptionWithErrorList {
        Operation operation = new Operation();
        ExceptionWithErrorList operationException = new ExceptionWithErrorList(operation);
        if((cardNumber == null) || (cardNumber.isEmpty()) || (cardNumber.length() != 16)){
            operationException.addMessage("err.card-number-is-invalid");
        }
        operation.setCardNumber(cardNumber);
        if((validityDate == null) || (validityDate.isEmpty()) || (validityDate.length() != 5)){
            operationException.addMessage("err.validity-date-is-invalid");
        }
        operation.setValidityDate(validityDate);
        if((cardCode == null) || (cardCode.isEmpty()) || (cardCode.length() != 3)){
            operationException.addMessage("err.card-code-is-invalid");
        }
        operation.setCardCode(cardCode);
        BigDecimal bigDecimalAmount;
        try{
            bigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
            operation.setAmount(bigDecimalAmount);
        } catch (NumberFormatException exc){
            operationException.addMessage("err.amount-is-invalid");
        }
        if(operationException.getErrorMessageList().size() != 0){
            throw operationException;
        }
        operation.setOperationType(OperationDAOImpl.INPUT);
        Connection connection = null;
        Savepoint savepoint = null;
        try{
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();
            operation.setUserId(userDAO.getUserIdByLogin(username));
            if(!operationDAO.canFillUpBalanceForUser(operation.getUserId())){
                operationException.addMessage("err.can-not-fill-up-because-time");
                throw operationException;
            }
            userDAO.fillUpBalanceForUser(connection, operation.getUserId(), operation.getAmount());
            operationDAO.addOperation(connection, operation);
            connection.commit();
        } catch (DAOException | SQLException exc){
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
                connectionPool.returnConnectionToPool(connection);
            }
        }
        return operation;
    }

    @Override
    public Operation withdrawMoney(String username, String cardNumber, String validityDate, String amount)
            throws ServiceException, ExceptionWithErrorList {
        Operation operation = new Operation();
        ExceptionWithErrorList operationException = new ExceptionWithErrorList(operation);
        if((cardNumber == null) || (cardNumber.isEmpty()) || (cardNumber.length() != 16)){
            operationException.addMessage("err.card-number-is-invalid");
        }
        operation.setCardNumber(cardNumber);
        if((validityDate == null) || (validityDate.isEmpty()) || (validityDate.length() != 5)){
            operationException.addMessage("err.validity-date-is-invalid");
        }
        operation.setValidityDate(validityDate);
        BigDecimal bigDecimalAmount;
        try{
            bigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
            operation.setAmount(bigDecimalAmount);
        } catch (NumberFormatException exc){
            operationException.addMessage("err.amount-is-invalid");
        }
        if(operationException.getErrorMessageList().size() != 0){
            throw operationException;
        }
        operation.setOperationType(OperationDAOImpl.OUTPUT);
        Connection connection = null;
        Savepoint savepoint = null;
        try{
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();
            operation.setUserId(userDAO.getUserIdByLogin(username));
            if(!userDAO.haveMoney(operation.getUserId(), operation.getAmount())){
                operationException.addMessage("err.can-not-withdraw-money-because-not-enough");
                throw operationException;
            }
            userDAO.withdrawMoneyFromUser(connection, operation.getUserId(), operation.getAmount());
            operationDAO.addOperation(connection, operation);
            connection.commit();
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
        return operation;
    }
}
