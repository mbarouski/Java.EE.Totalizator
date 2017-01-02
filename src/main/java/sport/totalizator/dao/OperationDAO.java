package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.OperationException;

public interface OperationDAO {
    Operation addOperation(Operation operation) throws DAOException, OperationException;

    boolean canFillUpBalanceForUser(int userId) throws DAOException;
}
