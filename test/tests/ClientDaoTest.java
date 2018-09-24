package tests;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import database.ConnectionManager;
import entity.Client;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientDaoTest {
    private ClientDao clientDao = ClientDaoImpl.getINSTANCE();


    @Test
    public void clientTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = clientDao.getAll().size();
        Client newClient = clientDao.save(new Client("John", "Dow", "35", 1));
        int afterSave = clientDao.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);

        newClient.setLastName("Daw");
        clientDao.update(newClient);
        Client updatedClient = clientDao.get(newClient.getId());
        Assert.assertEquals("Incorrect update method", "Daw", updatedClient.getLastName());

        Client client2 = clientDao.getClientByFullName("John", "Doe");
        Assert.assertNotNull(client2);

        clientDao.delete(newClient.getId());

        afterSave = clientDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }
}
