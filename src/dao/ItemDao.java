package dao;

import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends DAO<Item> {
    List<Item> getByItemId(int id) throws SQLException;

    List<Item> getByOrderId(int orderId) throws SQLException;
}
