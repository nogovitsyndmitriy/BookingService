package services.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import dao.vo.AccountVO;
import entity.Account;
import entity.Client;
import entity.Contact;
import entity.CreditCard;
import services.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountServiceImpl extends AbstractService implements AccountService {
    private AccountDao accountDao = AccountDaoImpl.getINSTANCE();
    private ContactService contactService = ContactServiceImpl.getINSTANCE();
    private CreditCardService creditCardService = CreditCardServiceImpl.getINSTANCE();
    private ClientService clientService = ClientServiceImpl.getINSTANCE();
    private static volatile AccountService INSTANCE = null;

    public static AccountService getINSTANCE() {
        AccountService accountService = INSTANCE;
        if (accountService == null) {
            synchronized (AccountDaoImpl.class) {
                accountService = INSTANCE;
                if (accountService == null) {
                    INSTANCE = accountService = new AccountServiceImpl();
                }
            }
        }
        return accountService;
    }

    @Override
    public AccountVO save(AccountVO account) {
        try {
            startTransaction();
            Account acc = new Account(account.getLogin(), account.getPassword(), account.getName(), account.getLastName(), account.getPassportId());
            accountDao.save(acc);
            Client client = new Client(account.getName(), account.getLastName(), account.getAge());
            clientService.save(client);
            Contact contact = new Contact(account.getAddress(), account.getPhone());
            contactService.save(contact);
            CreditCard creditCard = new CreditCard(account.getCardNumber(), account.getCardHolder());
            creditCardService.save(creditCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account get(Serializable id) {
        Account account = new Account();
        try {
            startTransaction();
            account = accountDao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public void update(Account account) {
        try {
            startTransaction();
            accountDao.update(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            accountDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Account> getAll() {
        List<Account> list = new LinkedList<>();
        try {
            startTransaction();
            list = accountDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Account getByLogin(String login) {
        try {
            startTransaction();
            return accountDao.getAccountByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Account by login: " + login, e);
        }
    }
}
