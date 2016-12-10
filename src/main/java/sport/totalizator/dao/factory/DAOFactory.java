package sport.totalizator.dao.factory;

import sport.totalizator.dao.*;
import sport.totalizator.dao.impl.*;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    public static DAOFactory getFactory(){
        return factory;
    }

    public UserDAO getUserDAO(){
        return UserDAOImpl.getInstance();
    }

    public CategoryDAO getCategoryDAO(){
        return CategoryDAOImpl.getInstance();
    }

    public EventDAO getEventDAO(){
        return EventDAOImpl.getInstance();
    }

    public LeagueDAO getLeagueDAO() {
        return LeagueDAOImpl.getInstance();
    }

    public MemberDAO getMemberDAO(){
        return MemberDAOImpl.getInstance();
    }
}
