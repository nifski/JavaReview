package com.example.part1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Appointments")
public class AppointmentRestController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return hospitalService.getAllAppointments();
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return hospitalService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return hospitalService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        return hospitalService.updateAppointment(id, updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        hospitalService.deleteAppointment(id);
    }

    @GetMapping("/{id}/medical-record")
    public MedicalRecord getMedicalRecordForAppointment(@PathVariable Long id) {
        return hospitalService.getMedicalRecordForAppointment(id);
    }
}
