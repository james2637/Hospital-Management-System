package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public void createdoctor(Doctor doctor){
        doctorRepo.save(doctor);
    }

    public List<Doctor> seeAllDoctors(){
        return doctorRepo.findAll();
    }

}
