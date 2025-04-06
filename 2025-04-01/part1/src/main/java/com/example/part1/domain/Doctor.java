package com.example.part1.domain;

@Entity
public class Doctor {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String specialisation;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;
}
