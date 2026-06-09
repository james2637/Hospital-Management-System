package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.dto.AppointmentDto;
import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.service.AppointmentService;
import com.rabibanik.hospitalManagementSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments/{doctorId}")
    public List<AppointmentDto> getAppointmentsByDoctorId(@PathVariable("doctorId") long doctorId, @RequestParam(value = "sort", defaultValue = "id") String sortby){
        return appointmentService.getAppointmentsByDoctorId(doctorId, Sort.by(Sort.Direction.DESC,sortby));
    }

    @GetMapping("/appointments/{doctorId}/{date}")
    public List<AppointmentDto> getAppointmentsByDoctorIdAndDate(@PathVariable("doctorId") long doctorId, @PathVariable("date") LocalDate date, @RequestParam(value = "sort", defaultValue = "id") String sortby){
        return appointmentService.getAppointmentsByDoctorIdAndDate(doctorId, date, Sort.by(sortby));
    }


}
