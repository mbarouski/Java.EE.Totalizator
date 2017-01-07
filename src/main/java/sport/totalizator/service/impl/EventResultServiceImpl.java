package sport.totalizator.service.impl;

import org.apache.log4j.Logger;
import sport.totalizator.entity.EventResult;
import sport.totalizator.exception.EventResultException;
import sport.totalizator.service.EventResultService;
import sport.totalizator.service.exception.ServiceException;

public class EventResultServiceImpl implements EventResultService {
    private static final EventResultServiceImpl instance = new EventResultServiceImpl();
    private static final Logger log = Logger.getLogger(EventResultServiceImpl.class);

    public static EventResultService getInstance(){
        return instance;
    }

    @Override
    public EventResult addEventResult(String eventId, String winnerId, String loserId, String winnerScore, String loserScore) throws ServiceException, EventResultException {
        return null;
    }
}
