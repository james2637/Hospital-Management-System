package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public Patient findPatientByName(String name){
        return patientRepo.findByName(name);
    }

    public Patient findPatientByEmail(String email){
        return patientRepo.findByEmail(email);
    }

    public List<Patient> findPatientWithBloodGroup(BloodGroupType bloodGroupType){
        return patientRepo.findAllPatientWithBloodGroup(bloodGroupType);
    }

    @Transactional
    public void removePatientInsurance(long id){

        Patient patient = patientRepo.findById(id).orElseThrow();

        patient.setInsurance(null);
    }
}
