package dao.impl;

import dao.ClientDao;
import entity.Client;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends AbstractDao implements ClientDao {
    private static volatile ClientDao INSTANCE = null;
    private static final String saveClientQuery = "INSERT INTO project.client(Name,LastName, Age) VALUES (?,?,?)";
    private static final String updateClientQuery = "UPDATE project.client SET Name = ?, LastName = ?, Age = ? WHERE Id = ?";
    private static final String getClientQuery = "SELECT * FROM project.client WHERE Id = ?";
    private static final String getAllClientsQuery = "SELECT * FROM project.client";
    private static final String getByFullNameQuery = "SELECT * FROM project.client WHERE Name = ? AND LastName = ?";
    private static final String deleteClientQuery = "DELETE FROM project.client WHERE Id = ?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psGetByFullName;
    private PreparedStatement psDelete;


    private ClientDaoImpl() {
    }

    public static ClientDao getINSTANCE() {
        ClientDao clientDao = INSTANCE;
        if (clientDao == null) {
            synchronized (ClientDaoImpl.class) {
                clientDao = INSTANCE;
                if (clientDao == null) {
                    INSTANCE = clientDao = new ClientDaoImpl();
                }
            }
        }
        return clientDao;
    }

    @Override
    public Client getClientByFullName(String name, String lastName) throws SQLException {
        psGetByFullName = preparedStatement(getByFullNameQuery);
        psGetByFullName.setString(1, name);
        psGetByFullName.setString(2, lastName);
        psGetByFullName.executeQuery();
        ResultSet rs = psGetByFullName.getResultSet();
        Client client = new Client();
        while (rs.next()) {
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAge(rs.getString(4));
        }
        close(rs);
        return client;

    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> list = new ArrayList<>();
        psGetAll = preparedStatement(getAllClientsQuery);
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Client client = new Client();
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAge(rs.getString(4));
            list.add(client);
        }
        close(rs);
        return list;
    }

    @Override
    public Client save(Client client) throws SQLException {
        psSave = preparedStatement(saveClientQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, client.getName());
        psSave.setString(2, client.getLastName());
        psSave.setString(3, client.getAge());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            client.setId(rs.getInt(1));
        }
        close(rs);
        return client;
    }

    @Override
    public Client get(Serializable id) throws SQLException {
        psGet = preparedStatement(getClientQuery);
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        Client client = new Client();
        if (rs.next()) {
            client.setId(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setLastName(rs.getString(3));
            client.setAge(rs.getString(4));
            return client;
        }
        close(rs);
        return client;
    }

    @Override
    public void update(Client client) throws SQLException {
        psUpdate = preparedStatement(updateClientQuery);
        psUpdate.setString(1, client.getName());
        psUpdate.setString(2, client.getLastName());
        psUpdate.setString(3, client.getAge());
        psUpdate.setInt(4, client.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteClientQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }
}
