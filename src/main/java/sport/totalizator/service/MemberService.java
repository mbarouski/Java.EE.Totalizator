package sport.totalizator.service;

import sport.totalizator.entity.Member;
import sport.totalizator.exception.ExceptionWithErrorList;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

/**
 * {@link MemberService} provides methods for interaction of controller and DAO layer from side of member.
 */
public interface MemberService {
    /**
     * Method returns list of {@link Member}s for {@link sport.totalizator.entity.League}.
     * @param leagueId
     * @return
     * @throws ServiceException
     */
    List<Member> getMembersByLeague(int leagueId) throws ServiceException;

    /**
     * Method returns list of {@link Member}s for {@link sport.totalizator.entity.Event}.
     * @param eventId
     * @return
     * @throws ServiceException
     */
    List<Member> getMembersByEvent(int eventId) throws ServiceException;

    /**
     * Method checks data for {@link Member} adding and manages this process.
     * @param name
     * @param categoryId
     * @param leagueId
     * @return
     * @throws ServiceException
     * @throws ExceptionWithErrorList
     */
    Member addMember(String name, String categoryId, String leagueId) throws ServiceException, ExceptionWithErrorList;
}
