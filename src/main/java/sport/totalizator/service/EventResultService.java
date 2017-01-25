package sport.totalizator.service;

import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

/**
 * {@link EventResultService} provides methods for interaction of controller and DAO layer from side of event result.
 */
public interface EventResultService {
    /**
     * Method checks data for {@link EventResult} adding and manages this process.
     * @param eventId
     * @param winnerId
     * @param loserId
     * @param winnerScore
     * @param loserScore
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    EventResult addEventResult(String eventId, String winnerId, String loserId, String winnerScore, String loserScore)
            throws ServiceException, ExceptionWithErrorList;
}
