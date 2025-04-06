package com.example.part1.domain;

@Entity
public class Patient {
    @Id @GeneratedValue
    private long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;
}
