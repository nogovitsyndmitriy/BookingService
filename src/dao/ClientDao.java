package dao;

import entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends DAO<Client> {
    Client getClientByFullName(String name, String lastName) throws SQLException;
    List<Client> getAll() throws SQLException;
}

