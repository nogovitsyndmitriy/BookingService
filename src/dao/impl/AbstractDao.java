package dao.impl;

import database.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDao {

    protected PreparedStatement preparedStatement(String query) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query);
    }

    protected PreparedStatement preparedStatement(String query, int flag) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query, flag);
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
