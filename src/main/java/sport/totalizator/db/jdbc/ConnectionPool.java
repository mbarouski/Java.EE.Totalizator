package sport.totalizator.db.jdbc;


import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final Properties properties = DBPropertiesReader.getDBProperties();
    private static final ConnectionPool connectionPool = new ConnectionPool(properties);

    private ArrayBlockingQueue<Connection> connections;
    private ReentrantLock lockForReturnConnection;

    public static ConnectionPool getConnectionPool(){
        return connectionPool;
    }

    private ConnectionPool(Properties properties){
        int poolSize = Integer.parseInt(properties.getProperty("poolSize"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        lockForReturnConnection = new ReentrantLock();
        Connection connection;
        for(int i = 0; i < poolSize; i++){
            try {
                connection = DriverManager.getConnection(url, username, password);
                connections.offer(connection);
            }
            catch (SQLException exc){
                log.error(exc);
            }
        }
    }

    public Connection getConnection(){
        Connection connection;
        try {
            connection = connections.take();
        }
        catch(InterruptedException exc){
            connection = null;
            log.error(exc);
        }
        return connection;
    }

    public void returnConnectionToPool(Connection c){
        lockForReturnConnection.lock();
        if(!connections.contains(c)) {
            connections.offer(c);
        }
        lockForReturnConnection.unlock();
    }
}
