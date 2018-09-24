package dao.impl;

import dao.ItemDao;
import entity.Item;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends AbstractDao implements ItemDao {
    private static volatile ItemDao INSTANCE = null;

    private static final String saveItemQuery = "INSERT INTO project.item (Account_Id,Room_Id, Quantity, Order_Id) VALUES (?, ?, ?,?)";
    private static final String updateItemQuery = "UPDATE project.item SET Quantity=? WHERE project.item.Id =?";
    private static final String getItemsByItemIdQuery = "SELECT * FROM project.item WHERE project.item.Id = ?";
    private static final String deleteItemQuery = "DELETE FROM project.item WHERE project.item.Id=?";
    private static final String getItemsByOrderQuery = "SELECT * FROM project.item WHERE Order_Id = ?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;


    @Override
    public List<Item> getByItemId(int id) throws SQLException {
        psGetAll = preparedStatement(getItemsByItemIdQuery);
        psGetAll.setInt(1, id);
        psGetAll.execute();
        List<Item> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public Item save(Item item) throws SQLException {
        psSave = preparedStatement(saveItemQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setInt(1, item.getAccountId());
        psSave.setInt(2, item.getRoomId());
        psSave.setInt(3, item.getQuantity());
        psSave.setInt(4, item.getOrderId());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            item.setId(rs.getInt(1));
        }
        close(rs);
        return item;
    }

    @Override
    public Item get(Serializable id) throws SQLException {
        psGet = preparedStatement(getItemsByItemIdQuery);
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
    public void update(Item item) throws SQLException {
        psUpdate = preparedStatement(updateItemQuery);
        psUpdate.setInt(3, item.getId());
        psUpdate.setInt(1, item.getQuantity());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteItemQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    public static ItemDao getInstance() {
        ItemDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new ItemDaoImpl();
                }
            }
        }

        return itemDao;
    }

    private Item populateEntity(ResultSet rs) throws SQLException {
        Item entity = new Item();
        entity.setId(rs.getInt(1));
        entity.setOrderId(rs.getInt(2));
        entity.setRoomId(rs.getInt(3));
        entity.setQuantity(rs.getInt(4));
        entity.setAccountId(rs.getInt(5));
        return entity;
    }

    @Override
    public List<Item> getByOrderId(int orderId) throws SQLException {
        psGetAll = preparedStatement(getItemsByOrderQuery);
        psGetAll.setInt(1, orderId);
        psGetAll.execute();
        List<Item> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }
}
