package database;

import java.sql.Connection;

public class ConnectionManager {
    private static ThreadLocal<Connection> threads = new ThreadLocal<>();

    public static Connection getConnection() throws DbManagerException {
        try {
            if (threads.get() == null) {
                threads.set(DataSource.getInstance().getConnection());
            }
            return threads.get();
        } catch (Exception e) {
            throw new DbManagerException("Connection failure" + e.getMessage());
        }
    }
}
