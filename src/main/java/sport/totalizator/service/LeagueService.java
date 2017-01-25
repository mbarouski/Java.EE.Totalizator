package sport.totalizator.service;

import sport.totalizator.entity.League;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

/**
 * {@link LeagueService} provides methods for interaction of controller and DAO layer from side of league.
 */
public interface LeagueService {
    /**
     * Method returns list of {@link League}s for {@link sport.totalizator.entity.Category}.
     * @param categoryId
     * @return
     * @throws ServiceException
     */
    List<League> getLeaguesByCategory(int categoryId) throws ServiceException;

    /**
     * Method checks data for {@link League} adding and manages this process.
     * @param name
     * @param categoryId
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    League addLeague(String name, String categoryId) throws ServiceException, ExceptionWithErrorList;
}
