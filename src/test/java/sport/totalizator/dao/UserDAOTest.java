package sport.totalizator.dao;


import org.junit.Test;
import sport.totalizator.dao.impl.UserDAOImpl;
import sport.totalizator.entity.User;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserDAOTest {
    @Test
    public void getAllUsersTest(){
        UserDAOImpl dao = UserDAOImpl.getInstance();
        List<User> users = dao.getAllUsers();
        assertEquals(true, users.size() > 0);
    }
}
