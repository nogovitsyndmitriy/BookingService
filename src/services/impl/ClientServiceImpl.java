package services.impl;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import entity.Client;
import services.ClientService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientServiceImpl extends AbstractService implements ClientService {
    private ClientDao clientDao = ClientDaoImpl.getINSTANCE();
    private static volatile ClientService INSTANCE = null;

    public static ClientService getINSTANCE() {
        ClientService clientService = INSTANCE;
        if (clientService == null) {
            synchronized (ClientDaoImpl.class) {
                clientService = INSTANCE;
                if (clientService == null) {
                    INSTANCE = clientService = new ClientServiceImpl();
                }
            }
        }
        return clientService;
    }


    @Override
    public Client get(Serializable id) {
        Client client = new Client();
        try {
            startTransaction();
            client = clientDao.get(id);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client save(Client client) {
        try {
            startTransaction();
            client = clientDao.save(client);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            clientDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(Client client) {
        try {
            startTransaction();
            clientDao.update(client);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getClientByFullName(String name, String lastName) {
        Client client = new Client();
        try {
            startTransaction();
            client = clientDao.getClientByFullName(name, lastName);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> list = new LinkedList<>();
        try {
            startTransaction();
            list = clientDao.getAll();
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
