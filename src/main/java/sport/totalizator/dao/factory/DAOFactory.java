package sport.totalizator.dao.factory;

import sport.totalizator.dao.CategoryDAO;
import sport.totalizator.dao.EventDAO;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.dao.impl.CategoryDAOImpl;
import sport.totalizator.dao.impl.EventDAOImpl;
import sport.totalizator.dao.impl.UserDAOImpl;

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
}
