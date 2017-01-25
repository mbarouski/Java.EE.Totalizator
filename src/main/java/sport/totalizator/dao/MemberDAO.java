package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Member;

import java.sql.Connection;
import java.util.List;

/**
 * MemberDAO is interface that defines methods for interaction of service layer with database from side of {@link Member}.
 */
public interface MemberDAO {
    /**
     * Method returns a list of {@link Member}s that belong to {@link sport.totalizator.entity.League}.
     * @param leagueId
     * @return
     * @throws DAOException
     */
    List<Member> getMembersByLeague(int leagueId) throws DAOException;

    /**
     * Method returns a list of {@link Member}s that belong to {@link sport.totalizator.entity.Event}.
     * @param eventId
     * @return
     * @throws DAOException
     */
    List<Member> getMembersByEvent(int eventId) throws DAOException;

    /**
     * Method attaches {@link Member}s to {@link sport.totalizator.entity.Event}.
     * @param connection
     * @param memberIds
     * @param eventId
     * @throws DAOException
     */
    void attachMembersToEvent(Connection connection, List<Integer> memberIds, int eventId) throws DAOException;

    /**
     * Method returns {@link Member}'s name by memberId.
     * @param memberId
     * @return
     * @throws DAOException
     */
    String getMemberNameById(int memberId) throws DAOException;

    /**
     * Method adds {@link Member} to database.
     * @param member
     * @return
     * @throws DAOException
     */
    Member addMember(Member member) throws DAOException;
}
