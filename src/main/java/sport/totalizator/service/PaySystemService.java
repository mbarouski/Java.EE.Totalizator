package sport.totalizator.service;

import sport.totalizator.entity.Operation;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

public interface PaySystemService {
    Operation fillUpBalance(String username, String cardNumber, String validityDate, String cardCode, String amount) throws ServiceException, ExceptionWithErrorList;

    Operation withdrawMoney(String username, String cardNumber, String validityDate, String amount) throws ServiceException, ExceptionWithErrorList;
}
