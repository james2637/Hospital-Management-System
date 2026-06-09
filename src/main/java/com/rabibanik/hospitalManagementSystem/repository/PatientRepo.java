package com.rabibanik.hospitalManagementSystem.repository;

import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.entity.type.BloodGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    Patient findByName(String name);
    Patient findByUserEmail(String email);    //its like using the WHERE in SQL So it like //SELECT * FROM patient WHERE email = "email"

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")     // to create our own query method
    List<Patient> findAllPatientWithBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);  // see this website to learn more https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    @Query(value = "Select * from patient", nativeQuery = true) // to create our own query method using native SQL query
    List<Patient> findAllPatient();

    List<Patient> findByOrderByCreatedAtDesc();  //this will sort the result by createdAt

}
