package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.dto.DoctorRegistrationDto;
import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import com.rabibanik.hospitalManagementSystem.service.AppointmentService;
import com.rabibanik.hospitalManagementSystem.service.DoctorService;
import com.rabibanik.hospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/patient/info")
    public Patient getPatientInfo(@RequestParam String name){
        return patientService.findPatientByName(name);
    }

    @GetMapping("/patient/info-email")
    public Patient getPatientInfoByEmail(@RequestParam String email) {
        return patientService.findPatientByEmail(email);
    }

    @GetMapping("/patientWithBloodGroup/{type}")
    public List<Patient> getPatientWithBloodGroup(@PathVariable("type") BloodGroupType bloodGroup){
        return patientService.findPatientWithBloodGroup(bloodGroup);
    }

    @PostMapping("/appointment/add/{doctorId}/{patientId}")
    public void createAppointment(@RequestBody Appointment appointment, @PathVariable("doctorId") long doctorId, @PathVariable("patientId") long patientId){

       appointmentService.createAppointment(appointment,doctorId, patientId);
    }

    @GetMapping("/appointment/get/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable("appointmentId") long id){

        return appointmentService.getAppointmentDetails(id);
    }

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<?> onBoardNewDoctor(@RequestBody DoctorRegistrationDto dto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(dto));
    }
}
