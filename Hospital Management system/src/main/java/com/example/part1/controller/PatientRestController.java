package com.example.part1.controller;

import com.example.part1.domain.Patient;
import com.example.part1.domain.Appointments;
import com.example.part1.domain.Record;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/patients")
public class PatientRestController {

    private final List<Patient> patients = new ArrayList<>();
    private final Map<Long, List<Appointments>> patientAppointments = new HashMap<>();
    private final Map<Long, List<Record>> patientRecords = new HashMap<>();
    private final AtomicLong patientIdCounter = new AtomicLong(1);
    private final AtomicLong appointmentIdCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return patients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        patient.setId(patientIdCounter.getAndIncrement());
        patients.add(patient);

        Appointments appt = new Appointments();
        appt.setId(appointmentIdCounter.getAndIncrement());
        appt.setAppointmentDate(Timestamp.valueOf("2025-04-25 15:00:00"));
        appt.setStatus("Scheduled");
        appt.setNotes("Auto-created appointment");
        appt.setPatient(patient); // for linking

        List<Appointments> appts = new ArrayList<>();
        appts.add(appt);
        patientAppointments.put(patient.getId(), appts);

        patientRecords.put(patient.getId(), new ArrayList<>());

        return ResponseEntity.status(201).body(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updated) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                updated.setId(id);
                patients.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean removed = patients.removeIf(p -> p.getId().equals(id));
        patientAppointments.remove(id);
        patientRecords.remove(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Patient controller is working!");
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointments>> getAppointmentsForPatient(@PathVariable Long id) {
        List<Appointments> appts = patientAppointments.get(id);
        return (appts == null || appts.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(appts);
    }

    @GetMapping("/{id}/medical-records")
    public ResponseEntity<List<Record>> getMedicalRecordsForPatient(@PathVariable Long id) {
        List<Record> records = patientRecords.get(id);
        return (records == null || records.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(records);
    }

    public Map<Long, List<Record>> getPatientRecordsMap() {
        return patientRecords;
    }

}

