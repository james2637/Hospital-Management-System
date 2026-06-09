package com.rabibanik.hospitalManagementSystem.dto;


import com.rabibanik.hospitalManagementSystem.entity.type.AuthProviderType;
import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {

    private String name;
    private String email;
    private String gender;
    private String phone;
    private BloodGroupType bloodGroup;
    private AuthProviderType signInWith;
    private LocalDateTime createdAt;
}
