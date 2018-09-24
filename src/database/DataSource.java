package database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static DataSource dataSource;
    private ComboPooledDataSource cpds;


    private DataSource() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("driver");
        cpds.setJdbcUrl("url");
        cpds.setUser("user");
        cpds.setPassword("password");
        //settings
        cpds.setMinPoolSize(2);
        cpds.setMaxPoolSize(20);
        cpds.setAcquireIncrement(5);
        cpds.setMaxStatements(200);
    }

    public static synchronized DataSource getInstance() throws PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
