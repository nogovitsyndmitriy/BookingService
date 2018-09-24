package dao.impl;

import dao.AccountDao;
import entity.Account;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AccountDaoImpl extends AbstractDao implements AccountDao {

    private static volatile AccountDao INSTANCE = null;

    private static final String saveAccountQuery = "INSERT INTO project.accounts(Login, Password, Name, LastName, PassportId) VALUES (?,?,?,?,?)";
    private static final String updateAccountQuery = "UPDATE project.accounts SET Login = ?, Password =?, PassportId = ?, Name = ?, LastName =?";
    private static final String getAccountQuery = "SELECT * FROM project.accounts WHERE ID =?";
    private static final String getAllAccountQuery = "SELECT * FROM project.accounts";
    private static final String deleteAccountQuery = "DELETE FROM project.accounts WHERE ID = ?";
    private static final String getAccountByLoginQuery = "SELECT * FROM project.accounts WHERE Login = ?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;
    private PreparedStatement psGetByLogin;


    private AccountDaoImpl() {

    }

    public static AccountDao getINSTANCE() {
        AccountDao accountDao = INSTANCE;
        if (accountDao == null) {
            synchronized (ClientDaoImpl.class) {
                accountDao = INSTANCE;
                if (accountDao == null) {
                    INSTANCE = accountDao = new AccountDaoImpl();
                }
            }
        }
        return accountDao;
    }

    @Override
    public List<Account> getAll() throws SQLException {
        psGetAll = preparedStatement(getAllAccountQuery);
        List<Account> list = new LinkedList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Account account = new Account();
            account.setID(rs.getInt(1));
            account.setLogin(rs.getString(2));
            account.setPassword(rs.getString(3));
            account.setName(rs.getString(4));
            account.setLastName(rs.getString(5));
            account.setPassportId(rs.getString(6));
            list.add(account);
        }
        close(rs);
        return list;
    }

    @Override
    public Account save(Account account) throws SQLException {
        psSave = preparedStatement(saveAccountQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, account.getLogin());
        psSave.setString(2, account.getPassword());
        psSave.setString(3, account.getName());
        psSave.setString(4, account.getLastName());
        psSave.setString(5, account.getPassportId());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            account.setID(rs.getInt(1));
        }
        close(rs);
        return account;
    }

    @Override
    public Account get(Serializable id) throws SQLException {
        psGet = preparedStatement(getAccountQuery);
        Account account = new Account();
        psGet.setInt(1, (int) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        while ((rs.next())) {
            account.setID(rs.getInt(1));
            account.setLogin(rs.getString(2));
            account.setPassword(rs.getString(3));
            account.setName(rs.getString(4));
            account.setLastName(rs.getString(5));
            account.setPassportId(rs.getString(6));
            return account;
        }
        close(rs);
        return account;
    }

    @Override
    public void update(Account account) throws SQLException {
        psUpdate = preparedStatement(updateAccountQuery);
        psUpdate.setString(1, account.getLogin());
        psUpdate.setString(2, account.getPassword());
        psUpdate.setString(3, account.getName());
        psUpdate.setString(4, account.getLastName());
        psUpdate.setString(5, account.getPassportId());
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteAccountQuery);
        psDelete.setInt(1, (int) id);
        return psDelete.executeUpdate();
    }

    @Override
    public Account getAccountByLogin(String login) throws SQLException {
        psGetByLogin = preparedStatement((getAccountByLoginQuery));
        psGetByLogin.setString(1, login);
        psGetByLogin.executeQuery();
        ResultSet rs = psGetByLogin.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return populateEntity(rs);
    }

    private Account populateEntity(ResultSet rs) throws SQLException {
        Account entity = new Account();
        entity.setID(rs.getInt(1));
        entity.setName(rs.getString(4));
        entity.setLogin(rs.getString(2));
        entity.setPassword(rs.getString(3));
        entity.setPassportId(rs.getString(6));
        entity.setLastName(rs.getString(5));
        return entity;
    }
}
