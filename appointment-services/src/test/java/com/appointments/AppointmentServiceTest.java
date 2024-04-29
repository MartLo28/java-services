package com.appointments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {
    private AppointmentService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }

    @Test
    void testAddAndGetAppointment() {
        Appointment newAppointment = new Appointment("A123", new Date(System.currentTimeMillis() + 10000), "Test");
        service.addAppointment(newAppointment);
        Appointment retrieved = service.getAppointment("A123");
        assertEquals(newAppointment, retrieved, "The retrieved appointment should match the added appointment.");
    }
    
    @Test
    void testDeleteAppointment() {
        Appointment newAppointment = new Appointment("A123", new Date(System.currentTimeMillis() + 10000), "Test");
        service.addAppointment(newAppointment);
        service.deleteAppointment("A123");
        assertNull(service.getAppointment("A123"), "The appointment should be deleted and thus not retrievable.");
    }

    @Test
    void testDeleteNonExistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("nonexistent"));
    }

    @Test
    void testAddAppointmentDetails() {
        Appointment appointment = new Appointment("A123", new Date(System.currentTimeMillis() + 10000), "Test");
        service.addAppointment(appointment);
        Appointment retrieved = service.getAppointment("A123");
        assertEquals(appointment.getDescription(), retrieved.getDescription(), "Description should match.");
        assertEquals(appointment.getAppointmentDate(), retrieved.getAppointmentDate(), "Dates should match.");
    }

    @Test
    void testAppointmentNotRetrievableAfterDeletion() {
        String id = "A123";
        Appointment appointment = new Appointment(id, new Date(System.currentTimeMillis() + 10000), "Test");
        service.addAppointment(appointment);
        service.deleteAppointment(id);
        assertNull(service.getAppointment(id), "Appointment should not be retrievable after deletion.");
    }

    @Test
    void testAddDuplicateAppointmentId() {
        Appointment firstAppointment = new Appointment("A123", new Date(System.currentTimeMillis() + 10000), "Initial Test");
        service.addAppointment(firstAppointment);
        Appointment duplicateAppointment = new Appointment("A123", new Date(System.currentTimeMillis() + 20000), "Duplicate Test");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addAppointment(duplicateAppointment));
        assertEquals("Appointment ID already exists", exception.getMessage(), "Expected duplicate ID insertion to throw an IllegalArgumentException with a specific message.");
    }

    @Test
    void testUpdateAppointmentDetails() {
        String id = "A123";
        Appointment appointment = new Appointment(id, new Date(System.currentTimeMillis() + 10000), "Initial Test");
        service.addAppointment(appointment);
        Date newDate = new Date(System.currentTimeMillis() + 20000);
        String newDescription = "Updated Test";
        assertDoesNotThrow(() -> service.updateAppointment(id, newDate, newDescription));
        Appointment updated = service.getAppointment(id);
        assertEquals(newDate, updated.getAppointmentDate(), "Expected updated date to match the new date set.");
        assertEquals(newDescription, updated.getDescription(), "Expected updated description to match the new description set.");
    }
    

    @Test
    void testRetrievingNonExistentAppointment() {
        assertNull(service.getAppointment("DoesNotExist"), "Retrieving a non-existent appointment should return null.");
    }

}

