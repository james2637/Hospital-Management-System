package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.PatientRegistrationDto;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.entity.type.RoleType;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PatientRepo patientRepo;

    public void createUserPatient(PatientRegistrationDto patientRegistrationDto){
//        Create and populate the Security User Entity
        User user = new User();
        user.setUsername(patientRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(patientRegistrationDto.getPassword()));
        user.setEmail(patientRegistrationDto.getEmail());
        user.setRoles(Set.of(RoleType.PATIENT));
        User savedUser = userRepo.save(user);

//        Create and populate the Patient Entity
        Patient patient = new Patient();
        patient.setName(patientRegistrationDto.getUsername());
        patient.setBloodGroup(patientRegistrationDto.getBloodGroup());
        patient.setGender(patientRegistrationDto.getGender());
        patient.setPhone(patientRegistrationDto.getPhone());
        patient.setUser(savedUser);   // Link the OnetoOne relationship
        patientRepo.save(patient);
    }
}
