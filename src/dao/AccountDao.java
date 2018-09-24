package dao;

import entity.Account;


import java.sql.SQLException;
import java.util.List;

public interface AccountDao extends DAO<Account> {
    List<Account> getAll() throws SQLException;

    Account getAccountByLogin(String login) throws SQLException;

}
