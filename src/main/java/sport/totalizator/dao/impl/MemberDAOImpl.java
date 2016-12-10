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

    private static final Logger log = Logger.getLogger(MemberDAOImpl.class);
    private static final MemberDAOImpl instance = new MemberDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static MemberDAOImpl getInstance(){
        return instance;
    }

    MemberDAOImpl(){}

    public Member createMember(ResultSet resultSet) throws SQLException{
        Member member = new Member();
        member.setName(resultSet.getString("name"));
        member.setId(resultSet.getInt("id"));
        return member;
    }

    @Override
    public List<Member> getMembersByLeague(int leagueId) throws DAOException {
        String sql = "SELECT `member_id` AS `id`, `member_name` AS `name` " +
                "FROM `eventmember` " +
                "WHERE `league_id` = ?;";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Member> result = new ArrayList<>();

        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, leagueId);
                statement.execute();
                try{
                    resultSet = statement.getResultSet();
                    while(resultSet.next()){
                        result.add(createMember(resultSet));
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
    public void attachMembersToEvent(List<Integer> memberIds, int eventId) throws DAOException {
        for(Integer memberId : memberIds){
            attachMemberToEvent(memberId, eventId);
        }
    }

    private void attachMemberToEvent(int memberId, int eventId) throws DAOException{
        String sql = "INSERT INTO `event_m2m_eventmember`(`event_id`, `member_id`) " +
                "VALUES (?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, eventId);
                statement.setInt(2, memberId);
                int result = statement.executeUpdate();
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
    }


}
