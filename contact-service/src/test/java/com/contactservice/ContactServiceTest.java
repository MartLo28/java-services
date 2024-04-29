package com.contactservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        Contact contact = new Contact("001", "John", "Doe", "1234567890", "Times Square");
        service.addContact(contact);
    }

    @Test
    void testAddContact() {
        Contact newContact = new Contact("002", "Jane", "Smith", "2345678901", "Times Square");
        service.addContact(newContact);
        assertEquals("Jane", service.getContact("002").getFirstName());
    }

    @Test
    void testDeleteContact() {
        service.deleteContact("001");
        assertNull(service.getContact("001"));
    }

    @Test
    void testUpdateContact() {
        service.updateContact("001", "Johnny", "Doe", "1234567890", "Times Square");
        assertEquals("Johnny", service.getContact("001").getFirstName());
    }

    @Test
    void testUpdateInvalidFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("001", null, "Doe", "1234567890", "Times Square");
        });
        assertTrue(exception.getMessage().contains("First name must not be null and must be less than or equal to 10 characters"));
    }

    @Test
    void testAddDuplicateContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(new Contact("001", "Jane", "Doe", "2345678901", "Times Square"));
        });
        assertTrue(exception.getMessage().contains("Contact already exists"));
    }

    @Test
    void testDeleteNonExistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("002");
        });
        assertTrue(exception.getMessage().contains("Contact does not exist"));
    }

    @Test
    void testUpdateNonExistentContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("002", "Johnny", "Doe", "1234567890", "Times Square");
        });
        assertTrue(exception.getMessage().contains("Contact does not exist"));
    }

    @Test
    void addContactWithNullValues() {
        ContactService service = new ContactService();
        String contactId = "0011223344";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(new Contact(contactId, null, null, "1234567890", "Times Square"));
        });

        assertTrue(thrown.getMessage().contains("First name must not be null"));
    }

    @Test
    void updateContactWithNullName() {
        ContactService service = new ContactService();
        String contactId = "0011223344";
        service.addContact(new Contact(contactId, "John", "Doe", "1234567890", "Times Square"));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(contactId, null, "Doe", "1234567890", "New Address");
        });

        assertTrue(thrown.getMessage().contains("First name must not be null"));
    }

    @Test
    void testAddDuplicateContactId() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "Times Square");
        service.addContact(contact1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact1);
        });
        assertTrue(exception.getMessage().contains("Contact already exists"));
    }

    @Test
    void testPartiallyValidUpdate() {
        service.addContact(new Contact("003", "Pablo", "Picasso", "9876543210", "Times Square"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("003", "Pablo", "Diego José Francisco de Paula Juan Nepomuceno María de los Remedios Cipriano de la Santísima Trinidad Ruiz y Picasso.", "9876543210", "Times Square");
        });
        assertTrue(exception.getMessage().contains("Last name must be less than or equal to 10 characters"));
    }

    @Test
    void testSequentialOperations() {
        Contact contact = new Contact("004", "John", "Doe", "8765432109", "Times Square");
        service.addContact(contact);
        service.updateContact("004", "John", "Doe", "8765432109", "Times Square");
        assertEquals("John", service.getContact("004").getFirstName());
        service.deleteContact("004");
        assertNull(service.getContact("004"));
    }

    @Test
    void testNonExistenceAfterDeletion() {
        Contact contact = new Contact("005", "John", "Doe", "1231231231", "Times Square");
        service.addContact(contact);
        service.deleteContact("005");
        assertThrows(IllegalArgumentException.class, () -> service.updateContact("005", "John", "Doe", "1231231231", "Times Square"));
    }

    @Test
    void testUpdateWithMultipleFieldErrors() {
        Contact contact = new Contact("006", "Dave", "Lopez", "9876543210", "Times Square");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("006", null, "TooLongLastNameeeee", "1234", "A very long address that should definitely be too long for the system");
        }, "Should throw an exception due to multiple invalid fields.");
    }

    @Test
    void testAddContactWithEdgeCaseNames() {
        String edgeCaseName = "JaneMaryDo"; // Exactly 10 characters
        assertDoesNotThrow(() -> {
            service.addContact(new Contact("007", edgeCaseName, "Smith", "9876543210", "Times Square"));
        });
        String tooLongName = "JohnMicheal"; // 11 characters
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(new Contact("008", tooLongName, "Smith", "9876543210", "Times Square"));
        }, "Should throw an exception due to first name being too long.");
    }

}
