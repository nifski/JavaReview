package com.example.part1.domain;

@Entity
public class Record {
    @Id @GeneratedValue
    private Long id;

    private String diagnosis;
    private String treatment;

    @OneToOne
    private Appointment appointment;

    @ManyToOne
    private Patient patient;
}
