package services;

import entity.Contact;

import java.io.Serializable;

public interface ContactService {
    Contact save(Contact contact);

    Contact get(Serializable id);

    void update(Contact contact);

    int delete(Serializable id);
}
