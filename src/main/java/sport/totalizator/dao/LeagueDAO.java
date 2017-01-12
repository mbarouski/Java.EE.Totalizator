package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.League;

import java.util.List;

public interface LeagueDAO {
    List<League> getLeaguesByCategory(int categoryId) throws DAOException;

    League addLeague(League league) throws DAOException;
}
