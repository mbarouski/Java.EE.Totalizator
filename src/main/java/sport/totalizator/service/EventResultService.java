package sport.totalizator.service;

import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

public interface EventResultService {
    EventResult addEventResult(String eventId, String winnerId, String loserId, String winnerScore, String loserScore)
            throws ServiceException, ExceptionWithErrorList;
}
