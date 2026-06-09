package com.rabibanik.hospitalManagementSystem.repository;

import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(long doctorId, Sort sort);

    List<Appointment> findByDoctorIdAndAppointmentDate(long doctorId, LocalDate appointmentDate, Sort sort);
}
