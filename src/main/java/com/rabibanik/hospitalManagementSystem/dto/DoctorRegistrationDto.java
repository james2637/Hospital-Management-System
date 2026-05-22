package com.rabibanik.hospitalManagementSystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorRegistrationDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String specialization;
}
