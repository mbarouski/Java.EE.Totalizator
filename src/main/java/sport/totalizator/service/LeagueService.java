package sport.totalizator.service;

import sport.totalizator.entity.League;
import sport.totalizator.exception.LeagueException;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public interface LeagueService {
    List<League> getLeaguesByCategory(int categoryId) throws ServiceException;

    League addLeague(String name, String categoryId) throws ServiceException, LeagueException;
}
