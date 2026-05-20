package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.entity.Insurance;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.InsuranceRepo;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private InsuranceRepo insuranceRepo;

    public void addPatientInsurance(long patientId, Insurance insurance){
        Patient patient = patientRepo.findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient not found with this id :" + patientId));

        patient.setInsurance(insurance);

        patientRepo.save(patient);
    }

}
