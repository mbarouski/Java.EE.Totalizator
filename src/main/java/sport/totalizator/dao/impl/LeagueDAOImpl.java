package sport.totalizator.dao.impl;

import org.apache.log4j.Logger;
import sport.totalizator.dao.LeagueDAO;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.db.jdbc.ConnectionPool;
import sport.totalizator.entity.League;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueDAOImpl implements LeagueDAO {
    private final static String SQL_FOR_ADD_LEAGUE = "INSERT INTO `league`(`event_category_id`, `league_name`) " +
            "VALUES(?, ?);";


    private static final Logger log = Logger.getLogger(LeagueDAOImpl.class);
    private static final LeagueDAOImpl instance = new LeagueDAOImpl();
    private static final ConnectionPool pool = ConnectionPool.getConnectionPool();

    public static LeagueDAOImpl getInstance(){
        return instance;
    }

    LeagueDAOImpl(){}

    private League createLeague(ResultSet resultSet) throws SQLException{
        League league = new League();
        league.setName(resultSet.getString("name"));
        league.setId(resultSet.getInt("id"));
        return league;
    }

    @Override
    public List<League> getLeaguesByCategory(int categoryId) throws DAOException {
        String sql = "SELECT `league_id` AS `id`, `league_name` AS `name` " +
                "FROM `league` " +
                "WHERE `event_category_id` = ?;";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<League> result = new ArrayList<>();
        try{
            connection = pool.getConnection();
            try{
                statement = connection.prepareStatement(sql);
                statement.setInt(1, categoryId);
                statement.execute();
                try{
                    resultSet = statement.getResultSet();
                    while (resultSet.next()){
                        result.add(createLeague(resultSet));
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
        } finally{
            if(connection != null){
                pool.returnConnectionToPool(connection);
            }
        }
        return result;
    }

    @Override
    public League addLeague(League league) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = pool.getConnection();
            try {
                statement = connection.prepareStatement(SQL_FOR_ADD_LEAGUE);
                statement.setInt(1, league.getCategoryId());
                statement.setString(2, league.getName());
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
        return league;
    }
}
