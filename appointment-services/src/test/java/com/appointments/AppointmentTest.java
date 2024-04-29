package com.appointments;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {
    @Test
    void testAppointmentCreationSuccess() {
        assertDoesNotThrow(() -> new Appointment("12345", new Date(System.currentTimeMillis() + 10000), "Test Description"));
    }

    @Test
    void testAppointmentIdValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, new Date(), "Test"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345678901", new Date(), "Test"));
    }

    @Test
    void testDescriptionValidation() {
        Exception nullException = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", new Date(System.currentTimeMillis() + 10000), null));
        assertTrue(nullException.getMessage().contains("Description cannot be null or more than 50 characters"));
    
        String longDescription = "This is a very long description that should cause an error to be thrown";
        Exception longDescriptionException = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", new Date(System.currentTimeMillis() + 10000), longDescription));
        assertTrue(longDescriptionException.getMessage().contains("Description cannot be null or more than 50 characters"));
    }

    @Test
    void testAppointmentDateNotNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", null, "Checkup"),
            "Appointment date cannot be null.");
    }

    @Test
    void testAppointmentDateNotPast() {
        Date pastDate = new Date(System.currentTimeMillis() - 1000); // 1 second in the past
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", pastDate, "Checkup"),
            "Appointment date cannot be in the past.");
    }

    @Test
    void testAppointmentIdMaxLength() {
        String maxId = "1234567890"; // Exactly 10 characters
        assertDoesNotThrow(() -> new Appointment(maxId, new Date(System.currentTimeMillis() + 10000), "Valid Description"));
    }

    @Test
    void testDescriptionMaxLength() {
        String maxDescription = "12345678901234567890123456789012345678901234567890"; // Exactly 50 characters
        assertDoesNotThrow(() -> new Appointment("12345", new Date(System.currentTimeMillis() + 10000), maxDescription));
    }

    @Test
    void testSetAppointmentDateInFuture() {
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 10000), "Valid Description");
        assertDoesNotThrow(() -> appointment.setAppointmentDate(new Date(System.currentTimeMillis() + 20000)));
    }

    @Test
    void testSetDescriptionValid() {
        Appointment appointment = new Appointment("12345", new Date(), "Initial");
        String newDescription = "New valid description";
        assertDoesNotThrow(() -> appointment.setDescription(newDescription));
    }

}

