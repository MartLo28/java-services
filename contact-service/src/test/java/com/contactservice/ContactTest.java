package com.contactservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testContactCreation() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertNotNull(contact);
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testContactIDNotNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
        assertTrue(exception.getMessage().contains("Contact ID must not be null and must be less than or equal to 10 characters"));
    }

    @Test
    void testFirstNameNotNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", null, "Doe", "1234567890", "123 Main St");
        });
        assertTrue(exception.getMessage().contains("First name must not be null and must be less than or equal to 10 characters"));
    }

    @Test
    void testPhoneValidation() {
        String invalidPhone = "12345abcde";  // This should fail as it's not purely numeric
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", invalidPhone, "123 Main St");
        });
        assertEquals("Phone must be exactly 10 digits and contain only numbers.", exception.getMessage());
    }
    
    @Test
    void testAddressValidation() {
        String overMaxAddress = "1234567890123456789012345678901";  // 31 characters, should fail
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", overMaxAddress);
        });
        assertEquals("Address must be less than or equal to 30 characters", exception.getMessage());
    }
    

    @Test
    void testLastNameNotNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", null, "1234567890", "123 Main St");
        });
        assertTrue(exception.getMessage().contains("Last name must not be null and must be less than or equal to 10 characters"));
    }
    @Test
    void testContactIDMaxLength() {
        String expectedMessage = "Contact ID must not be null and must be less than or equal to 10 characters.";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
        assertEquals(expectedMessage, thrown.getMessage(), "Error message does not match expected for Contact ID length.");
    }
    
    @Test
    void testFirstNameMaxLength() {
        String expectedMessage = "First name must not be null and must be less than or equal to 10 characters.";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "Johnathanathan", "Doe", "1234567890", "123 Main St");
        });
        assertEquals(expectedMessage, thrown.getMessage(), "Error message does not match expected for first name length.");
    }
    
    @Test
    void testLastNameMaxLength() {
        String expectedMessage = "Last name must not be null and must be less than or equal to 10 characters.";
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "DoeDoeDoeDoe", "1234567890", "123 Main St");
        });
        assertEquals(expectedMessage, thrown.getMessage(), "Error message does not match expected for last name length.");
    }

    @Test
    void testPhoneNumberFormat() {
        String invalidPhone = "12345abcde"; // Incorrect format
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", invalidPhone, "123 Main St");
        });
        assertTrue(exception.getMessage().contains("Phone must be exactly 10 digits and contain only numbers"));
    }

    @Test
    void testAddressMaxLength() {
        String maxAddress = "123456789012345678901234567890"; // Exactly 30 characters
        assertDoesNotThrow(() -> {
            new Contact("1234567890", "John", "Doe", "1234567890", maxAddress);
        });
    
        String overMaxAddress = "1234567890123456789012345678901"; // 31 characters
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", overMaxAddress);
        });
        assertTrue(exception.getMessage().contains("Address must be less than or equal to 30 characters"));
    }
    @Test
    void testSetFirstNameValid() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertDoesNotThrow(() -> contact.setFirstName("Jane"));
        assertEquals("Jane", contact.getFirstName(), "The first name should be updated to Jane.");
    }

    @Test
    void testSetLastNameAtMaxLength() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        String maxLastName = "DoeDoeDoeD"; // Exactly 10 characters
        assertDoesNotThrow(() -> contact.setLastName(maxLastName));
        assertEquals(maxLastName, contact.getLastName(), "The last name should be updated and match the maximum length.");
    }

    @Test
    void testSetPhoneWithValidNumber() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        String newPhone = "0987654321"; // Valid phone number
        assertDoesNotThrow(() -> contact.setPhone(newPhone));
        assertEquals(newPhone, contact.getPhone(), "The phone number should be updated to a valid 10-digit number.");
    }

    @Test
    void testAddressAtMaxLength() {
        String maxAddress = "123456789012345678901234567890"; // Exactly 30 characters
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", maxAddress);
        assertEquals(maxAddress, contact.getAddress(), "Address should be accepted at maximum length and match exactly.");
    }
    
}
