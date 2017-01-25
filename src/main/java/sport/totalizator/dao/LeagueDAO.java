package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.League;

import java.util.List;

/**
 * LeagueDAO is interface that defines methods for interaction of service layer with database from side of {@link League}.
 */
public interface LeagueDAO {
    /**
     * Method returns a list of {@link League}s that belong to {@link sport.totalizator.entity.Category}.
     * @param categoryId
     * @return
     * @throws DAOException
     */
    List<League> getLeaguesByCategory(int categoryId) throws DAOException;

    /**
     * Mathod adds {@link League} to database.
     * @param league
     * @return
     * @throws DAOException
     */
    League addLeague(League league) throws DAOException;
}
