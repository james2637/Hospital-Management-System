package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.entity.Insurance;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.service.InsuranceService;
import com.rabibanik.hospitalManagementSystem.service.PatientService;
import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private InsuranceService insuranceService;


    @GetMapping("/patientWithBloodGroup/{type}")
    public List<Patient> getPatientWithBloodGroup(@PathVariable("type") BloodGroupType bloodGroup){
        return patientService.findPatientWithBloodGroup(bloodGroup);
    }

    @PostMapping("/insurance/add/{id}")
    public void addInsuranceToPatient(@PathVariable("id") long patientId,@RequestBody Insurance insurance){
        insuranceService.addPatientInsurance(patientId, insurance);
    }


    @DeleteMapping("/insurance/delete/{id}")
    public void removePatientInsurance(@PathVariable("id") long patientId){
        patientService.removePatientInsurance(patientId);
    }
}
