package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/medical-records")
public class RecordRestController {

    private final AtomicLong idCounter = new AtomicLong(1);
    private final List<Record> records = new ArrayList<>();

    @Autowired
    private AppointmentRestController appointmentRestController;

    @Autowired
    private PatientRestController patientRestController;

    @PostMapping("/appointments/{appointmentId}")
    public ResponseEntity<Record> createRecord(@PathVariable Long appointmentId, @RequestBody Record record) {
        record.setId(idCounter.getAndIncrement());
        records.add(record);

        appointmentRestController.linkRecordToAppointment(appointmentId, record);

        Appointments appointment = appointmentRestController.getAppointments()
                .stream()
                .filter(a -> a.getId().equals(appointmentId))
                .findFirst()
                .orElse(null);

        if (appointment == null || appointment.getPatient() == null) {
            return ResponseEntity.badRequest().build();
        }

        Long patientId = appointment.getPatient().getId();
        Map<Long, List<Record>> patientRecords = patientRestController.getPatientRecordsMap();
        patientRecords.computeIfAbsent(patientId, k -> new ArrayList<>()).add(record);

        return ResponseEntity.status(201).body(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return records.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Record>> getAllRecords() {
        return records.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(records);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record updated) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId().equals(id)) {
                updated.setId(id);
                records.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        boolean removed = records.removeIf(r -> r.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
