package services.impl;

import dao.RoomsDao;
import dao.impl.RoomsDaoImpl;
import entity.Room;
import services.RoomService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class RoomServiceImpl extends AbstractService implements RoomService {
    private static volatile RoomService INSTANCE = null;
    private RoomsDao roomsDao = RoomsDaoImpl.getINSTANCE();


    public static RoomService getINSTANCE() {
        RoomService roomService = INSTANCE;
        if (roomService == null) {
            synchronized (RoomsDaoImpl.class) {
                roomService = INSTANCE;
                if (roomService == null) {
                    INSTANCE = roomService = new RoomServiceImpl();
                }
            }
        }
        return roomService;
    }

    @Override
    public Room save(Room room) {
        try {
            startTransaction();
            room = roomsDao.save(room);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public Room get(Serializable id) {
        Room room = new Room();
        try {
            startTransaction();
            room = roomsDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void update(Room room) {
        try {
            roomsDao.update(room);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            roomsDao.delete(id);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Room> getAll() {
        try {
            startTransaction();
            List<Room> list = roomsDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Rooms", e);
        }
    }
}
