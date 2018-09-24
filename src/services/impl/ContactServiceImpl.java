package services.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import services.ContactService;

import java.io.Serializable;
import java.sql.SQLException;

public class ContactServiceImpl extends AbstractService implements ContactService {

    private static volatile ContactService INSTANCE = null;
    private ContactDao contactDao = ContactDaoImpl.getINSTANCE();


    public static ContactService getINSTANCE() {
        ContactService contactService = INSTANCE;
        if (contactService == null) {
            synchronized (ContactServiceImpl.class) {
                contactService = INSTANCE;
                if (contactService == null) {
                    INSTANCE = contactService = new ContactServiceImpl();
                }
            }
        }
        return contactService;
    }


    @Override
    public Contact get(Serializable id) {
        Contact contact = new Contact();
        try {
            startTransaction();
            contact = contactDao.get(id);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        try {
            startTransaction();
            contact = contactDao.save(contact);
            commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contact;
    }

    @Override
    public void update(Contact contact) {
        try {
            startTransaction();
            contactDao.update(contact);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            contactDao.delete(id);
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
