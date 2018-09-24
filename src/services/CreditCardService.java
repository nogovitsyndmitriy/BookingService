package services;

import entity.CreditCard;

import java.io.Serializable;
import java.util.List;

public interface CreditCardService {

    CreditCard save(CreditCard creditCard);

    CreditCard get(Serializable id);

    void update(CreditCard creditCard);

    int delete(Serializable id);

    List<CreditCard> getAll();
}
