package sport.totalizator.service;

import sport.totalizator.entity.Member;
import sport.totalizator.exception.MemberException;
import sport.totalizator.service.exception.ServiceException;

import java.util.List;

public interface MemberService {
    List<Member> getMembersByLeague(int leagueId) throws ServiceException;

    List<Member> getMembersByEvent(int eventId) throws ServiceException;

    Member addMember(String name, String categoryId, String leagueId) throws ServiceException, MemberException;
}
