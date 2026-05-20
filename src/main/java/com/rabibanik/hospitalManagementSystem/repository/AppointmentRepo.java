package com.rabibanik.hospitalManagementSystem.repository;

import com.rabibanik.hospitalManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
