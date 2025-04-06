package com.example.part1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Doctor")
public class DoctorRestController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return hospitalService.getAllDoctors();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return hospitalService.createDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return hospitalService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        return hospitalService.updateDoctor(id, updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        hospitalService.deleteDoctor(id);
    }

    @GetMapping("/{id}/appointments")
    public List<Appointment> getAppointmentsForDoctor(@PathVariable Long id) {
        return hospitalService.getAppointmentsForDoctor(id);
    }
}
