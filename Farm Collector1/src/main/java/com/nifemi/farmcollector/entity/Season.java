package com.nifemi.farmcollector.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "season_year")
    private int year;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Planted> plantings;

    public Season() {
    }

    public Season(String name, int year, List<Planted> plantings) {
        this.name = name;
        this.year = year;
        this.plantings = plantings;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getYear() { return year; }
    public List<Planted> getPlantings() { return plantings; }

    public String getFullName() {
        return name + " " + year;
    }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setYear(int year) { this.year = year; }
    public void setPlantings(List<Planted> plantings) { this.plantings = plantings; }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}