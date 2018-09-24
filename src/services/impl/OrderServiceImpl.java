package services.impl;


import dao.ItemDao;
import dao.OrderDao;
import dao.RoomsDao;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.RoomsDaoImpl;
import entity.Item;
import entity.Order;
import entity.Room;
import services.OrderService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;

    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private RoomsDao roomsDao = RoomsDaoImpl.getINSTANCE();
    private ItemDao itemDao = ItemDaoImpl.getInstance();

    @Override
    public Order createOrder(int accountId, int roomId, int quantity) {
        try {
            startTransaction();
            Order order = new Order(accountId, quantity);
            Room room = roomsDao.get(roomId);
            order.setTotal(room.getCost() * quantity);
            order = orderDao.save(order);
            Item item = new Item(accountId, roomId, quantity, order.getId());
            itemDao.save(item);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order " + e);
        }
    }

    @Override
    public Order get(Serializable id) {
        try {
            return orderDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(Order order) {
        try {
            orderDao.update(order);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + order);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<Order> getByAccountId(int accountId) {
        try {
            startTransaction();
            List<Order> orders = orderDao.getByAccountId(accountId);
            for (Order order : orders) {
                List<Item> items = itemDao.getByOrderId(order.getId());
                order.setItems(items);
                double sum = 0;
                for (Item item : items) {
                    Room room = roomsDao.get(item.getRoomId());
                    sum += room.getCost() * item.getQuantity();
                }
                commit();
                order.setTotal(sum);
            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId" + accountId);
        }
    }

    public static OrderService getInstance() {
        OrderService orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }

        return orderService;
    }

    @Override
    public Order save(Order order) {
        try {
            startTransaction();
            order = orderDao.save(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
