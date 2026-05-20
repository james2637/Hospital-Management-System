package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.AppointmentRepo;
import com.rabibanik.hospitalManagementSystem.repository.DoctorRepo;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AppointmentService {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    public void createAppointment(Appointment appointment, long doctorId, long patientId){

        Patient patient = patientRepo.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appointmentRepo.save(appointment);
    }

    public Appointment getAppointmentDetails(long id){

        return appointmentRepo.findById(id).orElseThrow();
    }

}
