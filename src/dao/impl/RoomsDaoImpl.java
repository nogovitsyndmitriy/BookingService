package dao.impl;

import dao.RoomsDao;
import entity.Room;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RoomsDaoImpl extends AbstractDao implements RoomsDao {

    private static volatile RoomsDao INSTANCE = null;


    private final static String getAllRoomsQuery = "SELECT * FROM project.rooms";
    private final static String saveRoomQuery = "INSERT INTO project.rooms (Class, Places, Cost, isAvailable) VALUES (?,?,?,?)";
    private final static String getRoomQuery = "SELECT * FROM project.rooms WHERE ID =?";
    private final static String updateRoomQuery = "UPDATE project.rooms SET Classs = ?, Places = ?, Cost = ?, isAvailable = ? WHERE Id = ?";
    private final static String deleteRoomQuery = "DELETE FROM project.rooms WHERE ID = ?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAll;

    private RoomsDaoImpl() {
    }

    public static RoomsDao getINSTANCE() {
        RoomsDao roomsDao = INSTANCE;
        if (roomsDao == null) {
            synchronized (RoomsDaoImpl.class) {
                roomsDao = INSTANCE;
                if (roomsDao == null) {
                    INSTANCE = roomsDao = new RoomsDaoImpl();
                }
            }
        }
        return roomsDao;
    }


    @Override
    public List<Room> getAll() throws SQLException {
        List<Room> list = new LinkedList<>();
        psGetAll = preparedStatement(getAllRoomsQuery);
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Room room = new Room();
            room.setId(rs.getInt(1));
            room.setRoomClasss(rs.getString(2));
            room.setPlaces(rs.getString(3));
            room.setCost(rs.getDouble(4));
            room.setIsAvalible(rs.getString(5));
            list.add(room);
        }
        close(rs);
        return list;
    }

    @Override
    public Room save(Room room) throws SQLException {
        psSave = preparedStatement(saveRoomQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, room.getRoomClasss());
        psSave.setString(2, room.getPlaces());
        psSave.setDouble(3, room.getCost());
        psSave.setString(4, room.getIsAvalible());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            room.setId(rs.getInt(1));
        }
        close(rs);
        return room;
    }

    @Override
    public Room get(Serializable id) throws SQLException {
        psGet = preparedStatement(getRoomQuery);
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        Room room = new Room();
        if (rs.next()) {
            room.setId(rs.getInt(1));
            room.setRoomClasss(rs.getString(2));
            room.setPlaces(rs.getString(3));
            room.setCost(rs.getDouble(4));
            room.setIsAvalible(rs.getString(5));
            return room;
        }
        close(rs);
        return room;
    }

    @Override
    public void update(Room room) throws SQLException {
        psUpdate = preparedStatement(updateRoomQuery);
        psUpdate.setInt(1, room.getId());
        psUpdate.setString(2, room.getRoomClasss());
        psUpdate.setString(3, room.getPlaces());
        psUpdate.setDouble(4, room.getCost());
        psUpdate.setString(5, room.getIsAvalible());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteRoomQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }
}
