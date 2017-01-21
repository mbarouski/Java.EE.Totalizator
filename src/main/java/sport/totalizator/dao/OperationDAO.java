package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.OperationException;

import java.sql.Connection;

public interface OperationDAO {
    Operation addOperation(Connection connection, Operation operation) throws DAOException, OperationException;

    boolean canFillUpBalanceForUser(int userId) throws DAOException;
}
