package com.appointments;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class AppointmentService {
    private Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment ID already exists");
        }
        appointments.put(appointment.getAppointmentId(), appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID does not exist");
        }
        appointments.remove(appointmentId);
    }

    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    public void updateAppointment(String appointmentId, Date newDate, String newDescription) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID does not exist");
        }
        Appointment currentAppointment = appointments.get(appointmentId);
        currentAppointment.setAppointmentDate(newDate);
        currentAppointment.setDescription(newDescription);
    }
    
}
