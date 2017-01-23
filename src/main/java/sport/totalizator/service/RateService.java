package sport.totalizator.service;

import sport.totalizator.entity.Rate;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

public interface RateService {
    Rate makeRate(String type, String eventId, String username, String money,
                  String member1Id, String member1Score, String member2Id, String member2Score) throws ServiceException, ExceptionWithErrorList;
}
