package sport.totalizator.db.jdbc;

import org.junit.Test;

import java.sql.Connection;
import static junit.framework.TestCase.assertEquals;

public class ConnectionPoolTest {
    @Test
    public void ConnectionPoolTest() throws ConnectionPoolException{
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Connection c = pool.getConnection();
        pool.returnConnectionToPool(c);
        assertEquals(true, c != null);
    }
}
