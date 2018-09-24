package dao;

import entity.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomsDao extends DAO<Room> {
    List<Room> getAll() throws SQLException;
}
