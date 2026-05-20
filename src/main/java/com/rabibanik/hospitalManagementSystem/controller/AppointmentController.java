package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import com.rabibanik.hospitalManagementSystem.service.AppointmentService;
import com.rabibanik.hospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add/{doctorId}")
    public void createAppointment(@RequestBody Appointment appointment, @PathVariable("doctorId") long doctorId, @RequestParam("patientId") long patientId){

       appointmentService.createAppointment(appointment,doctorId, patientId);
    }

    @GetMapping("get/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable("appointmentId") long id){

        return appointmentService.getAppointmentDetails(id);
    }
}
