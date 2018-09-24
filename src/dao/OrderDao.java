package dao;

import entity.Order;

import java.sql.SQLException;
import java.util.List;


public interface OrderDao extends DAO<Order> {
    List<Order> getByAccountId(int accountId) throws SQLException;
}
