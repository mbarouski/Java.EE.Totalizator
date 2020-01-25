package sport.totalizator.dao;


import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.dao.impl.UserDAOImpl;
import sport.totalizator.entity.User;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@Ignore
public class UserDAOTest {
    private static final Logger log = Logger.getLogger(UserDAOTest.class);

    @Test
    public void getAllUsersTest(){
        List<User> users = null;
        UserDAOImpl dao = UserDAOImpl.getInstance();
        try {
            users = dao.getAllUsers();
        } catch (DAOException exc){
            log.error(exc);
        }
        assertEquals(true, users.size() > 0);
    }
}
