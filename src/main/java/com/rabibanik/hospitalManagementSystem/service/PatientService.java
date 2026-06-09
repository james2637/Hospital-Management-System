package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.PatientResponseDto;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public Patient findPatientByName(String name){
        return patientRepo.findByName(name);
    }

    public Patient findPatientByEmail(String email){
        return patientRepo.findByUserEmail(email);
    }

    public List<Patient> findPatientWithBloodGroup(BloodGroupType bloodGroupType){
        return patientRepo.findAllPatientWithBloodGroup(bloodGroupType);
    }

    public List<PatientResponseDto> findAllPatient() {

        List<Patient> patients = patientRepo.findByOrderByCreatedAtDesc();

        return patients.stream()
                .map(patient -> {
                    PatientResponseDto patientResponseDto = new PatientResponseDto();
                    patientResponseDto.setName(patient.getName());
                    patientResponseDto.setEmail(patient.getUser().getEmail());
                    patientResponseDto.setGender(patient.getGender());
                    patientResponseDto.setPhone(patient.getPhone());
                    patientResponseDto.setBloodGroup(patient.getBloodGroup());
                    patientResponseDto.setSignInWith(patient.getUser().getProviderType());
                    patientResponseDto.setCreatedAt(patient.getCreatedAt());

                    return patientResponseDto;
                })
                .toList();
    }

    @Transactional
    public void removePatientInsurance(long id){

        Patient patient = patientRepo.findById(id).orElseThrow();

        patient.setInsurance(null);
    }
}
