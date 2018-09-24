package tests;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import database.ConnectionManager;
import entity.Account;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountDaoTest {
    private AccountDao accountDao = AccountDaoImpl.getINSTANCE();


    @Test
    public void accountTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = accountDao.getAll().size();
        Account newAcc = accountDao.save(new Account("John", "Weak", "dmitriy", "fsf", "fsd"));
        int afterSave = accountDao.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);

        newAcc.setName("Dmitriy");
        accountDao.update(newAcc);

        Account updatedAccount = accountDao.get(newAcc.getID());
        Assert.assertEquals("Incorrect update method", "dmitriy", updatedAccount.getName());

        accountDao.delete(newAcc.getID());
        afterSave = accountDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);

    }
}
