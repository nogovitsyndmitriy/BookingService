package dao.impl;

import dao.OrderDao;
import entity.Order;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends AbstractDao implements OrderDao {

    private static volatile OrderDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO project.order (Account_Id, Total) VALUES (?,?)";
    private static final String updateQuery = "UPDATE `ORDER` SET Quantity=? WHERE ID=?";
    private static final String getQuery = "SELECT * FROM `ORDER` WHERE ID=?";
    private static final String psGetAllByAccountIdQuery = "SELECT * FROM project.order WHERE Account_Id = ? ORDER BY ID DESC";
    private static final String deleteQuery = "DELETE FROM `ORDER` WHERE ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAllByAccountId;
    private PreparedStatement psDelete;

    @Override
    public Order save(Order order) throws SQLException {
        psSave = preparedStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setInt(1, order.getAccountId());
        psSave.setDouble(2, order.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setId(rs.getInt(1));
        }
        close(rs);
        return order;
    }

    @Override
    public Order get(Serializable id) throws SQLException {
        psGet = preparedStatement(getQuery);
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Order order) throws SQLException {
        psUpdate = preparedStatement(updateQuery);
        psUpdate.setInt(1, order.getId());
        psUpdate.setDouble(2, order.getTotal());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Order> getByAccountId(int accountId) throws SQLException {
        psGetAllByAccountId = preparedStatement(psGetAllByAccountIdQuery);
        psGetAllByAccountId.setInt(1, accountId);
        psGetAllByAccountId.execute();
        ResultSet rs = psGetAllByAccountId.getResultSet();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    private Order populateEntity(ResultSet rs) throws SQLException {
        Order entity = new Order();
        entity.setId(rs.getInt(1));
        entity.setAccountId(rs.getInt(2));
        entity.setTotal(rs.getDouble(3));
        return entity;
    }

    public static OrderDao getInstance() {
        OrderDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new OrderDaoImpl();
                }
            }
        }
        return itemDao;
    }
}

