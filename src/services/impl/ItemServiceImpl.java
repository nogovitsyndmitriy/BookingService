package services.impl;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import entity.Item;
import services.ItemService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemServiceImpl extends AbstractService implements ItemService {
    private ItemDao itemDao = ItemDaoImpl.getInstance();
    public static volatile ItemService INSTANCE = null;

    @Override
    public Item save(Item item) {
        try {
            item = itemDao.save(item);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + item, e);
        }
        return item;
    }

    @Override
    public Item get(Serializable id) {
        try {
            return itemDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Item by id " + id, e);
        }
    }

    @Override
    public void update(Item item) {
        try {
            itemDao.update(item);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + item, e);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<Item> getByItemId(int id) {
        List<Item> list = new LinkedList<>();
        try {
            startTransaction();
            list = itemDao.getByItemId(id);
            commit();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all items", e);
        }
        return list;
    }

    public static ItemService getINSTANCE() {
        ItemService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemDaoImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new ItemServiceImpl();
                }
            }
        }
        return itemService;
    }
}
