package sport.totalizator.service;

import sport.totalizator.entity.Rate;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

/**
 * {@link RateService} provides methods for interaction of controller and DAO layer from side of rate.
 */
public interface RateService {
    /**
     * Method checks data for rate making and manages this process.
     * @param type
     * @param eventId
     * @param username
     * @param money
     * @param member1Id
     * @param member1Score
     * @param member2Id
     * @param member2Score
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Rate makeRate(String type, String eventId, String username, String money,
                  String member1Id, String member1Score, String member2Id, String member2Score) throws ServiceException, ExceptionWithErrorList;
}
