package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.OperationDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.factory.DAOFactory;
import sport.totalizator.dao.impl.OperationDAOImpl;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.OperationException;
import sport.totalizator.service.PaySystemService;
import sport.totalizator.service.exception.ServiceException;

import java.math.BigDecimal;

public class PaySystemServiceImpl implements PaySystemService {
    private static final PaySystemServiceImpl instance = new PaySystemServiceImpl();
    private static final Logger log = Logger.getLogger(PaySystemServiceImpl.class);

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
    public Operation fillUpBalance(String username, String cardNumber, String validityDate, String cardCode, String amount) throws ServiceException, OperationException {
        Operation operation = new Operation();
        OperationException operationException = new OperationException(operation);
        if((cardNumber == null) || (cardNumber.isEmpty()) || (cardNumber.length() != 16)){
            operationException.addErrorMessage("err.card-number-is-invalid");
        }
        operation.setCardNumber(cardNumber);
        if((validityDate == null) || (validityDate.isEmpty()) || (validityDate.length() != 5)){
            operationException.addErrorMessage("err.validity-date-is-invalid");
        }
        operation.setValidityDate(validityDate);
        if((cardCode == null) || (cardCode.isEmpty()) || (cardCode.length() != 3)){
            operationException.addErrorMessage("err.card-code-is-invalid");
        }
        operation.setCardCode(cardCode);
        BigDecimal bigDecimalAmount;
        try{
            bigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
            operation.setAmount(bigDecimalAmount);
        } catch (NumberFormatException exc){
            operationException.addErrorMessage("err.amount-is-invalid");
        }
        if(operationException.getErrorMessageList().size() != 0){
            throw operationException;
        }
        operation.setOperationType(OperationDAOImpl.INPUT);
        try{
            operation.setUserId(userDAO.getUserIdByLogin(username));
            if(!operationDAO.canFillUpBalanceForUser(operation.getUserId())){
                operationException.addErrorMessage("err.can-not-fill-up-because-time");
                throw operationException;
            }
            userDAO.fillUpBalanceForUser(operation);
            operationDAO.addOperation(operation);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return operation;
    }

    @Override
    public Operation withdrawMoney(String username, String cardNumber, String validityDate, String amount) throws ServiceException, OperationException {
        Operation operation = new Operation();
        OperationException operationException = new OperationException(operation);
        if((cardNumber == null) || (cardNumber.isEmpty()) || (cardNumber.length() != 16)){
            operationException.addErrorMessage("err.card-number-is-invalid");
        }
        operation.setCardNumber(cardNumber);
        if((validityDate == null) || (validityDate.isEmpty()) || (validityDate.length() != 5)){
            operationException.addErrorMessage("err.validity-date-is-invalid");
        }
        operation.setValidityDate(validityDate);
        BigDecimal bigDecimalAmount;
        try{
            bigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
            operation.setAmount(bigDecimalAmount);
        } catch (NumberFormatException exc){
            operationException.addErrorMessage("err.amount-is-invalid");
        }
        if(operationException.getErrorMessageList().size() != 0){
            throw operationException;
        }
        operation.setOperationType(OperationDAOImpl.OUTPUT);
        try{
            operation.setUserId(userDAO.getUserIdByLogin(username));
            if(!userDAO.canWithdrawMoney(operation)){
                operationException.addErrorMessage("err.can-not-withdraw-money-because-not-enough");
                throw operationException;
            }
            userDAO.withdrawMoneyFromUser(operation);
            operationDAO.addOperation(operation);
        } catch (DAOException exc){
            log.error(exc);
            throw new ServiceException(exc);
        }
        return operation;
    }
}
