package com.example.part1.controller;

import com.example.part1.domain.Doctor;
import com.example.part1.domain.Appointments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/doctors")
public class DoctorRestController {

    private final List<Doctor> doctors = new ArrayList<>();
    private final Map<Long, List<Appointments>> doctorAppointments = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return doctors.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(doctors);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        doctor.setId(idCounter.getAndIncrement());
        doctors.add(doctor);
        doctorAppointments.put(doctor.getId(), new ArrayList<>());
        return ResponseEntity.status(201).body(doctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updated) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                updated.setId(id);
                doctors.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean removed = doctors.removeIf(d -> d.getId().equals(id));
        doctorAppointments.remove(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointments>> getAppointmentsForDoctor(@PathVariable Long id) {
        List<Appointments> appts = doctorAppointments.get(id);
        return (appts == null || appts.isEmpty()) ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(appts);
    }
}
