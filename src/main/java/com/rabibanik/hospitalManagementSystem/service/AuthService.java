package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.LoginRequestDto;
import com.rabibanik.hospitalManagementSystem.dto.LoginResponseDto;
import com.rabibanik.hospitalManagementSystem.dto.PatientRegistrationDto;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.entity.type.AuthProviderType;
import com.rabibanik.hospitalManagementSystem.entity.type.RoleType;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import com.rabibanik.hospitalManagementSystem.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.lang.runtime.SwitchBootstraps;
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

        if(userRepo.findByEmail(patientRegistrationDto.getEmail()) != null && userRepo.findByUsername(patientRegistrationDto.getUsername()) != null){
            throw new IllegalArgumentException("User with this email already exits");
        }

//        Create and populate the Security User Entity
        User user = new User();
        user.setUsername(patientRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(patientRegistrationDto.getPassword()));
        user.setEmail(patientRegistrationDto.getEmail());
        user.setRoles(Set.of(RoleType.PATIENT));
        user.setProviderType(AuthProviderType.Email);
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

    public ResponseEntity<LoginResponseDto> handleOAuth2Login(OAuth2User oAuth2User, String resigtrationId) {
        //fetch provider type and provider id
        AuthProviderType providerType = switch (resigtrationId.toLowerCase()) {
            case "google" -> AuthProviderType.Google;
            case "github" -> AuthProviderType.Github;
//            case "facebook" -> AuthProviderType.Facebook;
//            case "twitter" -> AuthProviderType.Twitter;
            default -> AuthProviderType.Email;
        };

//        String providerId = switch (resigtrationId.toLowerCase()){       //if you want to store the provider id also
//            case "google" -> oAuth2User.getAttribute("sub");
//            case "github" -> oAuth2User.getAttribute("id").toString();
//            default -> throw new IllegalArgumentException("Unsupported oauth2 provider");
//        };
        String email = oAuth2User.getAttribute("email");

        User user = userRepo.findByEmail(email);

        //if the user has an account :directly login
        //otherwise first signup and then login

        if(user == null){
            user = new User();
            user.setUsername(oAuth2User.getAttribute("name"));
            user.setPassword("");
            user.setEmail(email);
            user.setRoles(Set.of(RoleType.PATIENT));
            user.setProviderType(providerType);
            User savedOauthUser = userRepo.save(user);     // after user continue with google is successful we can show him a screen to add the patient info
            //for now we will just create the patient with name and add the details we have after that we will show a pop up to the user to add other details like DOB, bloodGroup
            Patient patient = new Patient();
            patient.setName(oAuth2User.getAttribute("name"));
            patient.setUser(savedOauthUser);
            patientRepo.save(patient);

        }else if(user != null){
            if(user.getProviderType() != providerType){
                throw new BadCredentialsException("user already exits with different provider \n please login with "+ user.getProviderType());
            }
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(jwtUtil.generateToken(user), user.getId());

        return ResponseEntity.ok(loginResponseDto);
    }
}
