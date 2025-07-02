package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int year;

    public String getFullName() {
        return name + " " + year;
    }

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Planted> plantings;
}

