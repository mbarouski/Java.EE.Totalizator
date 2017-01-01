package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Rate;

import java.util.List;

public interface RateDAO {
    List<Rate> getActiveRatesForUser(int userId) throws DAOException;

    List<Rate> getFinishedRatesForUser(int userId) throws DAOException;
}
