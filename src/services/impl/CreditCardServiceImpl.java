package services.impl;

import dao.CreditCardDao;
import dao.impl.CreditCardDaoImpl;
import entity.CreditCard;
import services.CreditCardService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CreditCardServiceImpl extends AbstractService implements CreditCardService {

    private CreditCardDao creditCardDao = CreditCardDaoImpl.getInstance();
    private static volatile CreditCardService INSTANCE = null;

    public static CreditCardService getINSTANCE() {
        CreditCardService creditCardService = INSTANCE;
        if (creditCardService == null) {
            synchronized (CreditCardDaoImpl.class) {
                creditCardService = INSTANCE;
                if (creditCardService == null) {
                    INSTANCE = creditCardService = new CreditCardServiceImpl();
                }
            }
        }
        return creditCardService;
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        try {
            startTransaction();
            creditCard = creditCardDao.save(creditCard);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCard;
    }

    @Override
    public CreditCard get(Serializable id) {
        CreditCard creditCard = new CreditCard();
        try {
            startTransaction();
            creditCard = creditCardDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCard;
    }

    @Override
    public void update(CreditCard creditCard) {
        try {
            startTransaction();
            creditCardDao.update(creditCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            return creditCardDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<CreditCard> getAll() {
        List<CreditCard> list = new LinkedList<>();
        try {
            startTransaction();
            list = creditCardDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
