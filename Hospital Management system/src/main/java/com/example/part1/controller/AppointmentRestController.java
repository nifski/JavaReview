package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Record;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/appointments")
public class AppointmentRestController {

    private final List<Appointments> appointments = new ArrayList<>();
    private final Map<Long, Record> records = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Appointments>> getAllAppointments() {
        return appointments.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointment) {
        appointment.setId(idCounter.getAndIncrement());
        appointments.add(appointment);
        return ResponseEntity.status(201).body(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable Long id) {
        return appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointments> updateAppointment(@PathVariable Long id, @RequestBody Appointments updated) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equals(id)) {
                updated.setId(id);
                appointments.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        boolean removed = appointments.removeIf(a -> a.getId().equals(id));
        records.remove(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/medical-record")
    public ResponseEntity<Record> getRecordForAppointment(@PathVariable Long id) {
        // First, check the map
        Record record = records.get(id);
        if (record != null) {
            return ResponseEntity.ok(record);
        }

        // If not found in map, check the appointment directly
        Optional<Appointments> appt = appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (appt.isPresent() && appt.get().getMedicalRecord() != null) {
            return ResponseEntity.ok(appt.get().getMedicalRecord());
        }

        return ResponseEntity.notFound().build();
    }

    public void linkRecordToAppointment(Long appointmentId, Record record) {
        // Save to map (for backward compatibility)
        records.put(appointmentId, record);

        // Also link to the appointment object
        appointments.stream()
                .filter(a -> a.getId().equals(appointmentId))
                .findFirst()
                .ifPresent(a -> a.setMedicalRecord(record));
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }
}

