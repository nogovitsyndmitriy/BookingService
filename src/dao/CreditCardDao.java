package dao;

import entity.CreditCard;

import java.sql.SQLException;
import java.util.List;

public interface CreditCardDao extends DAO<CreditCard> {
    List<CreditCard> getAll() throws SQLException;
}
