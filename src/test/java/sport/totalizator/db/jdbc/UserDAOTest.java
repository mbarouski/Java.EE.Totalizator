package sport.totalizator.db.jdbc;


import org.junit.Test;
import sport.totalizator.dao.UserDAO;
import sport.totalizator.entity.User;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserDAOTest {
    @Test
    public void getAllUsersTest(){
        UserDAO dao = UserDAO.getInstance();
        List<User> users = dao.getAllUsers();
        assertEquals(true, users.size() > 0);
    }
}
