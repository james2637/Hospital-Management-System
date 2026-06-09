package com.rabibanik.hospitalManagementSystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppointmentDto {

    private long id;
    private String doctorName;
    private String doctorEmail;
    private String doctorPhone;
    private String patientName;
    private String patientPhone;
    private String reason;
    private LocalDateTime appointmentTime;
    private LocalDate appointmentDate;
}
