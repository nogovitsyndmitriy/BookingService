package dao.impl;

import dao.ContactDao;
import entity.Contact;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDaoImpl extends AbstractDao implements ContactDao {

    private static volatile ContactDao INSTANCE = null;

    private static final String getContactQuery = "SELECT * FROM project.contacts WHERE Id =?";
    private static final String saveContactQuery = "INSERT INTO project.contacts(Address, Phone) VALUES(?,?)";
    private static final String updateContactQuery = "UPDATE project.contacts SET Address = ?, Phone = ? WHERE Id = ?";
    private static final String deleteContactQuery = "DELETE FROM project.contacts WHERE Id = ?";

    private PreparedStatement psGet;
    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;

    private ContactDaoImpl() {
    }


    public static ContactDao getINSTANCE() {
        ContactDao contactDao = INSTANCE;
        if (contactDao == null) {
            synchronized (ContactDaoImpl.class) {
                contactDao = INSTANCE;
                if (contactDao == null) {
                    INSTANCE = contactDao = new ContactDaoImpl();
                }
            }
        }
        return contactDao;
    }

    @Override
    public Contact get(Serializable id) throws SQLException {
        psGet = preparedStatement(getContactQuery);
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        Contact contact = new Contact();
        if (rs.next()) {
            contact.setId(rs.getInt(1));
            contact.setAddress(rs.getString(2));
            contact.setPhone(rs.getString(3));
            return contact;
        }
        close(rs);
        return contact;
    }

    @Override
    public Contact save(Contact contact) throws SQLException {
        psSave = preparedStatement(saveContactQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, contact.getAddress());
        psSave.setString(2, contact.getPhone());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        while (rs.next()) {
            contact.setId(rs.getInt(1));
        }
        close(rs);
        return contact;
    }

    @Override
    public void update(Contact contact) throws SQLException {
        psUpdate = preparedStatement(updateContactQuery);
        psUpdate.setString(1, contact.getAddress());
        psUpdate.setString(2, contact.getPhone());
        psUpdate.setInt(3, contact.getId());
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteContactQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }
}
