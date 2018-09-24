package services;

import entity.Order;

import java.io.Serializable;
import java.util.List;


public interface OrderService {

    Order createOrder(int accountId, int roomId, int quantity);

    Order get(Serializable id);

    void update(Order order);

    int delete(Serializable id);

    List<Order> getByAccountId(int accountId);

    Order save(Order order);
}
