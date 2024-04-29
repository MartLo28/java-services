package com.contactservice;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact is null.");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact already exists.");
        }
        contacts.put(contact.getContactID(), contact);
    }
    
    public void deleteContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact does not exist.");
        }
        contacts.remove(contactID);
    }
    
    public void updateContact(String contactID, String firstName, String lastName, String phone, String address) {
        Contact contact = getContact(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact does not exist.");
        }
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);
    }
    

    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }
}
