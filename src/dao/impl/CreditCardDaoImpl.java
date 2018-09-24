package dao.impl;

import dao.CreditCardDao;
import entity.CreditCard;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CreditCardDaoImpl extends AbstractDao implements CreditCardDao {

    private static volatile CreditCardDao INSTANCE = null;
    private static final String saveCreditCardQuery = "INSERT INTO project.creditcards (CreditNumber, CardHolder) VALUES (?,?)";
    private static final String getCreditCardQuery = "SELECT * FROM project.creditcards WHERE Id = ?";
    private static final String updateCreditCardQuery = "UPDATE project.creditcards SET CreditNumber=?, CardHolder =? WHERE Id =?";
    private static final String deleteCreditCardQuery = "DELETE FROM project.creditcards WHERE  Id =?";
    private static final String getAllCreditCardQuery = "SELECT * FROM project.creditcards";

    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAll;


    public static CreditCardDao getInstance() {
        CreditCardDao creditCardDao = INSTANCE;
        if (creditCardDao == null) {
            synchronized (CreditCardDaoImpl.class) {
                creditCardDao = INSTANCE;
                if (creditCardDao == null) {
                    INSTANCE = creditCardDao = new CreditCardDaoImpl();
                }
            }
        }
        return creditCardDao;
    }

    @Override
    public CreditCard save(CreditCard creditCard) throws SQLException {
        psSave = preparedStatement(saveCreditCardQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, creditCard.getCardNumber());
        psSave.setString(2, creditCard.getCardHolder());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            creditCard.setId(rs.getInt(1));
        }
        close(rs);
        return creditCard;
    }

    @Override
    public CreditCard get(Serializable id) throws SQLException {
        psGet = preparedStatement(getCreditCardQuery);
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        CreditCard creditCard = new CreditCard();
        if (rs.next()) {
            creditCard.setId(rs.getInt(1));
            creditCard.setCardNumber(rs.getString(2));
            creditCard.setCardHolder(rs.getString(3));
            return creditCard;
        }
        close(rs);
        return creditCard;
    }

    @Override
    public void update(CreditCard creditCard) throws SQLException {
        psUpdate = preparedStatement(updateCreditCardQuery);
        psUpdate.setString(1, creditCard.getCardNumber());
        psUpdate.setString(2, creditCard.getCardNumber());
        psUpdate.setInt(3, creditCard.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteCreditCardQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<CreditCard> getAll() throws SQLException {
        psGetAll = preparedStatement(getAllCreditCardQuery);
        List<CreditCard> list = new LinkedList<>();
        CreditCard creditCard = new CreditCard();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            creditCard.setId(rs.getInt(1));
            creditCard.setCardNumber(rs.getString(2));
            creditCard.setCardHolder(rs.getString(3));
            list.add(creditCard);
        }
        close(rs);
        return list;
    }
}
