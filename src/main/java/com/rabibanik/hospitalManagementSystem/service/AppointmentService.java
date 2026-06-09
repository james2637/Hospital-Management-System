package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.dto.AppointmentDto;
import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.AppointmentRepo;
import com.rabibanik.hospitalManagementSystem.repository.DoctorRepo;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<AppointmentDto> getAllAppointments() {

        return appointmentRepo.findAll().stream().map(appointment -> {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(appointment.getId());
            appointmentDto.setDoctorName(appointment.getDoctor().getName());
            appointmentDto.setDoctorEmail(appointment.getDoctor().getUser().getEmail());
            appointmentDto.setDoctorPhone(appointment.getDoctor().getPhone());
            appointmentDto.setPatientName(appointment.getPatient().getName());
            appointmentDto.setPatientPhone(appointment.getPatient().getPhone());
            appointmentDto.setReason(appointment.getReason());
            appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
            appointmentDto.setAppointmentDate(appointment.getAppointmentDate());

            return appointmentDto;
        }).toList();
    }

    public void deleteAppointment(long id) {
        appointmentRepo.deleteById(id);
    }

    public List<AppointmentDto> getAppointmentsByDoctorId(long doctorId, Sort sort) {
        return appointmentRepo.findByDoctorId(doctorId, sort).stream().map(appointment -> {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(appointment.getId());
            appointmentDto.setDoctorName(appointment.getDoctor().getName());
            appointmentDto.setDoctorEmail(appointment.getDoctor().getUser().getEmail());
            appointmentDto.setDoctorPhone(appointment.getDoctor().getPhone());
            appointmentDto.setPatientName(appointment.getPatient().getName());
            appointmentDto.setPatientPhone(appointment.getPatient().getPhone());
            appointmentDto.setReason(appointment.getReason());
            appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
            appointmentDto.setAppointmentDate(appointment.getAppointmentDate());

            return appointmentDto;
        }).toList();
    }

    public List<AppointmentDto> getAppointmentsByDoctorIdAndDate(long doctorId, LocalDate date, Sort sort) {
        return appointmentRepo.findByDoctorIdAndAppointmentDate(doctorId, date, sort).stream().map(appointment -> {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(appointment.getId());
            appointmentDto.setDoctorName(appointment.getDoctor().getName());
            appointmentDto.setDoctorEmail(appointment.getDoctor().getUser().getEmail());
            appointmentDto.setDoctorPhone(appointment.getDoctor().getPhone());
            appointmentDto.setPatientName(appointment.getPatient().getName());
            appointmentDto.setPatientPhone(appointment.getPatient().getPhone());
            appointmentDto.setReason(appointment.getReason());
            appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
            appointmentDto.setAppointmentDate(appointment.getAppointmentDate());

            return appointmentDto;
        }).toList();
    }
}
