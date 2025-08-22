package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.model.Consultations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.dto.AssignDoctorDTO;
import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.AppointmentsRepository;
import com.patients.patientsMgt.services.AppointmentsService;
import com.patients.patientsMgt.services.DoctorsService;
import com.patients.patientsMgt.services.UsersService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentsService appointmentsService;

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AppointmentsRepository appointmentsRepository;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Appointments> appointmentsPage = appointmentsService.getAllAppointments(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", appointmentsPage.getContent());
        response.put("CurrentPage", appointmentsPage.getNumber());
        response.put("TotalItems", appointmentsPage.getTotalElements());
        response.put("TotalPages", appointmentsPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getAppointmentById/{id}")
    public Optional<Appointments> getAppointmentById(@PathVariable Long id) {
        return appointmentsService.getAppointmentById(id);
    }

    @PutMapping(value = "/updateAppointment/{id}")
    public Appointments updateAppointment(@PathVariable Long id, @RequestBody Appointments appointment) {
        appointment.setAppointmentId(id);
        return appointmentsService.makeAppointment(appointment);
    }

    @DeleteMapping(value = "/deleteAppointment/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentsService.deleteAppointment(id);
    }

    @PutMapping(value = "/{appointmentId}/assign-doctor")
    public ResponseEntity<?> assignDoctor(@PathVariable Long appointmentId, @RequestBody AssignDoctorDTO dto) {
        appointmentsService.assignDoctorToAppointment(appointmentId, dto.getDoctorId());
        return ResponseEntity.ok("Doctor assigned successfully");
    }


    @GetMapping(value= "/doctor")
    public ResponseEntity<?> getDoctorAppointments(Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUser(username);

        Doctors doctor = doctorsService.findByUser(user);
        if (doctor == null) {
            return ResponseEntity.badRequest().body("Doctor record not found");
        }

        List<Appointments> appointments = appointmentsRepository.findByDoctor(doctor);
        return ResponseEntity.ok(appointments);
    }

} 