package com.example.part1.domain;

@Entity
public class Appointments {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;
    private String status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalRecord medicalRecord;
}
