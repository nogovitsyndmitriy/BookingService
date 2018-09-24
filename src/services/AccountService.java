package services;

import dao.vo.AccountVO;
import entity.Account;

import java.io.Serializable;
import java.util.List;

public interface AccountService {
    AccountVO save(AccountVO account);

    Account get(Serializable id);

    void update(Account account);

    int delete(Serializable id);

    List<Account> getAll();

    Account getByLogin(String login);
}
