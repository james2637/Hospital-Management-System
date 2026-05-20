package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.service.PatientService;
import com.rabibanik.hospitalManagementSystem.type.BloodGroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/info")
    public Patient getPatientInfo(@RequestParam String name){
        return patientService.findPatientByName(name);
    }

    @GetMapping("/info-email")
    public Patient getPatientInfoByEmail(@RequestParam String email) {
        return patientService.findPatientByEmail(email);
    }

    @GetMapping("/patientWithBloodGroup")
    public List<Patient> getPatientWithBloodGroup(BloodGroupType bloodGroup){
        return patientService.findPatientWithBloodGroup(bloodGroup);
    }

    @DeleteMapping("/delete-Patient-Insurance/{id}")
    public void removePatientInsurance(@PathVariable("id") long patientId){
        patientService.removePatientInsurance(patientId);
    }
}
