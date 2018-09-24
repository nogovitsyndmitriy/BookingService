package tests;

import dao.CreditCardDao;
import dao.impl.CreditCardDaoImpl;
import database.ConnectionManager;
import entity.CreditCard;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class CreditCardDaoTest {
    private CreditCardDao creditCardDao = CreditCardDaoImpl.getInstance();

    @Test
    public void creditCardTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.setAutoCommit(true);
        int beforeSave = creditCardDao.getAll().size();
        CreditCard newCard = creditCardDao.save(new CreditCard("1111111111111111", "Vasiliy Pupkin"));
        int afterSave = creditCardDao.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);

        newCard.setCardHolder("James Bond");
        creditCardDao.update(newCard);
        CreditCard updatedCard = creditCardDao.get(newCard.getId());
        Assert.assertEquals("Incorrect updated method", "1111111111111111", updatedCard.getCardNumber());

        creditCardDao.delete(newCard.getId());

        afterSave = creditCardDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);
    }
}
