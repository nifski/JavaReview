package com.example.part1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Record")
public class MedicalRecordRestController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return hospitalService.createMedicalRecord(medicalRecord);
    }
}
