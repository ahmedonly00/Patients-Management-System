package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    public Optional<Appointments> getAppointmentById(Long id) {
        return appointmentsRepository.findById(id);
    }

    public Appointments saveAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentsRepository.deleteById(id);
    }

    public String updateAppointmentStatus(Long id, Appointments.Status status) {
        Optional<Appointments> appointmentOpt = appointmentsRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointments appointment = appointmentOpt.get();
            appointment.setStatus(status);
            appointmentsRepository.save(appointment);
            return "Appointment status updated successfully.";
        } else {
            return "Appointment not found.";
        }
    }
}
