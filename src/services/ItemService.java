package services;

import entity.Item;

import java.io.Serializable;
import java.util.List;

public interface ItemService {

    Item save(Item item);

    Item get(Serializable id);

    void update(Item item);

    int delete(Serializable id);

    List<Item> getByItemId(int id);
}
