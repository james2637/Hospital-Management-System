package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.dto.DoctorResponseDto;
import com.rabibanik.hospitalManagementSystem.dto.PatientRegistrationDto;
import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import com.rabibanik.hospitalManagementSystem.service.DoctorService;
import com.rabibanik.hospitalManagementSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AuthService authService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello from Hospital Management System, API Working";
    }

    @GetMapping("/getAllDoctors")
    public List<DoctorResponseDto> getAllDoctors(){
        return doctorService.seeAllDoctors();
    }

    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@RequestBody PatientRegistrationDto patientRegistrationDto){
        authService.createUserPatient(patientRegistrationDto);
        return ResponseEntity.ok("Patient Account Created ");
    }

}
