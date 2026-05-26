package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.LoginRequestDto;
import com.rabibanik.hospitalManagementSystem.dto.LoginResponseDto;
import com.rabibanik.hospitalManagementSystem.dto.PatientRegistrationDto;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.entity.type.RoleType;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import com.rabibanik.hospitalManagementSystem.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public void createUserPatient(PatientRegistrationDto patientRegistrationDto){

        if(patientRepo.findByUserEmail(patientRegistrationDto.getEmail()) != null && userRepo.findByUsername(patientRegistrationDto.getUsername()) != null){
            throw new IllegalArgumentException("User with this email already exits");
        }

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


    public LoginResponseDto loginUser(LoginRequestDto dto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user);

        return new LoginResponseDto(token,user.getId());

    }
}
