package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentsService appointmentsService;

    @GetMapping(value = "/all")
    public List<Appointments> getAllAppointments() {
        return appointmentsService.getAllAppointments();
    }

    @GetMapping(value = "getAppointmentById/{id}")
    public Optional<Appointments> getAppointmentById(@PathVariable Long id) {
        return appointmentsService.getAppointmentById(id);
    }

    @PostMapping(value = "/createAppointment")
    public Appointments createAppointment(@RequestBody Appointments appointment) {
        return appointmentsService.saveAppointment(appointment);
    }

    @PutMapping(value = "/updateAppointment/{id}")
    public Appointments updateAppointment(@PathVariable Long id, @RequestBody Appointments appointment) {
        appointment.setAppointmentId(id);
        return appointmentsService.saveAppointment(appointment);
    }

    @DeleteMapping(value = "/deleteAppointment/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentsService.deleteAppointment(id);
    }
} 