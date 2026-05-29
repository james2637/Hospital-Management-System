package com.rabibanik.hospitalManagementSystem.dto;


import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegistrationDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private BloodGroupType bloodGroup;
}
