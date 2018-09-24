package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.RoomService;
import services.impl.RoomServiceImpl;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    int id;
    String roomClasss;
    String places;
    Double cost;
    String isAvalible;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomClass='" + roomClasss + '\'' +
                ", places='" + places + '\'' +
                ", cost=" + cost +
                ", isAvalible='" + isAvalible + '\'' +
                '}';
    }
}
