package services;

import entity.Room;

import java.io.Serializable;
import java.util.List;

public interface RoomService {

    Room save(Room room);

    Room get(Serializable id);

    void update(Room room);

    int delete(Serializable id);

    List<Room> getAll();
}
