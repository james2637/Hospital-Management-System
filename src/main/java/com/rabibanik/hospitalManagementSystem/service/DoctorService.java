package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.DoctorRegistrationDto;
import com.rabibanik.hospitalManagementSystem.dto.DoctorResponseDto;
import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.entity.type.RoleType;
import com.rabibanik.hospitalManagementSystem.repository.DoctorRepo;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserRepo userRepo;

    public List<DoctorResponseDto> seeAllDoctors(){

        List<Doctor> doctors = doctorRepo.findAll();

        return doctors.stream()
                .map(doctor -> {
                    DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
                    doctorResponseDto.setName(doctor.getName());
                    doctorResponseDto.setSpecialization(doctor.getSpecialization());
                    doctorResponseDto.setEmail(doctor.getUser().getEmail());
                    doctorResponseDto.setPhone(doctor.getPhone());

                    return doctorResponseDto;
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public String createDoctor(DoctorRegistrationDto doctorRegistrationDto) {

        if (userRepo.findByUsername(doctorRegistrationDto.getUsername()) != null){
            throw new IllegalArgumentException("Doctor with this username already exits");
        }

        User user = new User();
        user.setUsername(doctorRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(doctorRegistrationDto.getPassword()));
        user.setEmail(doctorRegistrationDto.getEmail());
        user.setRoles(Set.of(RoleType.DOCTOR));
        User savedUser = userRepo.save(user);

        Doctor doctor = new Doctor();
        doctor.setName(doctorRegistrationDto.getUsername());
        doctor.setSpecialization(doctorRegistrationDto.getSpecialization());
        doctor.setPhone(doctorRegistrationDto.getPhone());
        doctor.setUser(savedUser);  // Link the OnetoOne relationship
        doctorRepo.save(doctor);

        return "Doctor Created Successfully";
    }

    public void removeDoctor(long doctorId) {
        Doctor doctor= doctorRepo.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doctorId));
        doctorRepo.deleteById(doctor.getId());
        userRepo.delete(doctor.getUser());
    }
}
