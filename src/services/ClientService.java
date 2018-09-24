package services;

import entity.Client;

import java.io.Serializable;
import java.util.List;

public interface ClientService {
    Client save(Client client);

    Client get(Serializable id);

    void update(Client client);

    int delete(Serializable id);

    Client getClientByFullName(String name, String lastName);

    List<Client> getAll();
}
