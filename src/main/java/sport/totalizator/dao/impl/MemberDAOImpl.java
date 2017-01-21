package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.MemberDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    private static final String SQL_FOR_GET_MEMBER_NAME_BY_ID = "SELECT `member_name` " +
            "FROM `eventmember` " +
            "WHERE `member_id` = ?;";
    private static final String SQL_FOR_ADD_MEMBER = "INSERT INTO `eventmember`(`league_id`, `member_name`) " +
            "VALUES(?, ?);";
    private static final String SQL_FOR_GET_MEMBERS_BY_LEAGUE = "SELECT `member_id` AS `id`, `member_name` AS `name` " +
            "FROM `eventmember` " +
            "WHERE `league_id` = ?;";
    private static final String SQL_FOR_ATTACH_MEMBER_TO_EVENT = "INSERT INTO `event_m2m_eventmember`(`event_id`, `member_id`) " +
            "VALUES (?, ?);";
    private static final String SQL_FOR_GET_MEMBERS_BY_EVENT = "SELECT `eventmember`.`member_id`, `member_name` AS `name` " +
            "FROM `eventmember` " +
            "JOIN `event_m2m_eventmember` " +
            "ON `eventmember`.`member_id` = `event_m2m_eventmember`.`member_id` " +
            "WHERE `event_m2m_eventmember`.`event_id` = ?;";

    private static final Logger log = Logger.getLogger(MemberDAOImpl.class);
    private static final MemberDAOImpl instance = new MemberDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static MemberDAOImpl getInstance(){
        return instance;
    }

    MemberDAOImpl(){}

    @Override
    public List<Member> getMembersByLeague(int leagueId) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Member> result = new ArrayList<>();

        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(SQL_FOR_GET_MEMBERS_BY_LEAGUE);
                statement.setInt(1, leagueId);
                statement.execute();
                try{
                    resultSet = statement.getResultSet();
                    Member member;
                    while(resultSet.next()){
                        member = new Member();
                        member.setName(resultSet.getString("name"));
                        member.setId(resultSet.getInt("id"));
                        result.add(member);
                    }
                } catch (SQLException exc){
                    log.error(exc);
                    throw new DAOException(exc);
                } finally {
                    if(resultSet != null){
                        resultSet.close();
                    }
                }
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }

        return result;
    }


    @Override
    public void attachMembersToEvent(Connection connection, List<Integer> memberIds, int eventId) throws DAOException {
        for(Integer memberId : memberIds){
            attachMemberToEvent(connection, memberId, eventId);
        }
    }

    private void attachMemberToEvent(Connection connection, int memberId, int eventId) throws DAOException{
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FOR_ATTACH_MEMBER_TO_EVENT);
            statement.setInt(1, eventId);
            statement.setInt(2, memberId);
            statement.executeUpdate();
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException exc) {
                    log.error(exc);
                }
            }
        }
    }

    @Override
    public List<Member> getMembersByEvent(int eventId) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Member> result = new ArrayList<>();
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(SQL_FOR_GET_MEMBERS_BY_EVENT);
                statement.setInt(1, eventId);
                statement.execute();
                try{
                    resultSet = statement.getResultSet();
                    Member member;
                    while(resultSet.next()){
                        member = new Member();
                        member.setName(resultSet.getString("name"));
                        member.setId(resultSet.getInt("member_id"));
                        result.add(member);
                    }
                } catch (SQLException exc){
                    log.error(exc);
                    throw new DAOException(exc);
                } finally {
                    if(resultSet != null){
                        resultSet.close();
                    }
                }
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }

        return result;
    }

    @Override
    public String getMemberNameById(int memberId) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String result = null;
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(SQL_FOR_GET_MEMBER_NAME_BY_ID);
                statement.setInt(1, memberId);
                statement.execute();
                try{
                    resultSet = statement.getResultSet();
                    if(resultSet.next()){
                        result = resultSet.getString("member_name");
                    }
                } catch (SQLException exc){
                    log.error(exc);
                    throw new DAOException(exc);
                } finally {
                    if(resultSet != null){
                        resultSet.close();
                    }
                }
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }

        return result;
    }

    @Override
    public Member addMember(Member member) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(SQL_FOR_ADD_MEMBER);
                statement.setInt(1, member.getLeagueId());
                statement.setString(2, member.getName());
                statement.executeUpdate();
            } catch (SQLException exc){
                log.error(exc);
                throw new DAOException(exc);
            } finally {
                if(statement != null){
                    statement.close();
                }
            }
        } catch (SQLException exc){
            log.error(exc);
            throw new DAOException(exc);
        } finally {
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }
        return member;
    }
}
