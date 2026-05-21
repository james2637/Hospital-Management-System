package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.service.AppointmentService;
import com.rabibanik.hospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient/info")
    public Patient getPatientInfo(@RequestParam String name){
        return patientService.findPatientByName(name);
    }

    @GetMapping("/patient/info-email")
    public Patient getPatientInfoByEmail(@RequestParam String email) {
        return patientService.findPatientByEmail(email);
    }

    @PostMapping("/appointment/add/{doctorId}/{patientId}")
    public void createAppointment(@RequestBody Appointment appointment, @PathVariable("doctorId") long doctorId, @PathVariable("patientId") long patientId){

       appointmentService.createAppointment(appointment,doctorId, patientId);
    }

    @GetMapping("/appointment/get/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable("appointmentId") long id){

        return appointmentService.getAppointmentDetails(id);
    }
}
