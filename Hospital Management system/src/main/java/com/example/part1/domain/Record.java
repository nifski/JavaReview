package com.example.part1.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;

    @OneToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointments appointment;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Timestamp getRecordDate() { return recordDate; }
    public void setRecordDate(Timestamp recordDate) { this.recordDate = recordDate; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Appointments getAppointment() { return appointment; }
    public void setAppointment(Appointments appointment) { this.appointment = appointment; }
}

