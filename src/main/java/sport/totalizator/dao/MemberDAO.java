package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Member;

import java.sql.Connection;
import java.util.List;

public interface MemberDAO {
    List<Member> getMembersByLeague(int leagueId) throws DAOException;

    List<Member> getMembersByEvent(int eventId) throws DAOException;

    void attachMembersToEvent(Connection connection, List<Integer> memberIds, int eventId) throws DAOException;

    String getMemberNameById(int memberId) throws DAOException;

    Member addMember(Member member) throws DAOException;
}
