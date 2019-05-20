package com.voyager.shop.service;

import com.voyager.shop.model.Contact;
import com.voyager.shop.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contactService")
public class ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact findContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
