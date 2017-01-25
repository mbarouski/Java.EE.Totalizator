package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Operation;
import sport.totalizator.exception.ExceptionWithErrorList;

import java.sql.Connection;

/**
 * OperationDAO is interface that defines methods for interaction of service layer with database from side of {@link Operation}.
 */
public interface OperationDAO {
    /**
     * Method adds {@link Operation} to database.
     * @param connection
     * @param operation
     * @return
     * @throws DAOException
     */
    Operation addOperation(Connection connection, Operation operation) throws DAOException;

    /**
     * Method checks availability of balance filling up process.
     * @param userId
     * @return
     * @throws DAOException
     */
    boolean canFillUpBalanceForUser(int userId) throws DAOException;
}
