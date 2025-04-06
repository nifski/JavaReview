package com.example.part1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Patient")
public class PatientRestController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return hospitalService.getAllPatients();
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return hospitalService.createPatient(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return hospitalService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return hospitalService.updatePatient(id, updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        hospitalService.deletePatient(id);
    }

    @GetMapping("/{id}/appointments")
    public List<Appointment> getAppointmentsForPatient(@PathVariable Long id) {
        return hospitalService.getAppointmentsForPatient(id);
    }

    @GetMapping("/{id}/medical-records")
    public List<MedicalRecord> getMedicalRecordsForPatient(@PathVariable Long id) {
        return hospitalService.getMedicalRecordsForPatient(id);
    }
}
