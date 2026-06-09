package com.rabibanik.hospitalManagementSystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorResponseDto {

    private String name;
    private String specialization;
    private String email;
    private String phone;
}
