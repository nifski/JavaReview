package com.example.part1.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialisation;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointments> appointments;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialisation() { return specialisation; }
    public void setSpecialisation(String specialisation) { this.specialisation = specialisation; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<Appointments> getAppointments() { return appointments; }
    public void setAppointments(List<Appointments> appointments) { this.appointments = appointments; }
}
