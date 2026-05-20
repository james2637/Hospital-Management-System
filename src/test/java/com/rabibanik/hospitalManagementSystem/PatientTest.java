package com.rabibanik.hospitalManagementSystem;

import com.rabibanik.hospitalManagementSystem.entity.Patient;
import com.rabibanik.hospitalManagementSystem.repository.PatientRepo;
import com.rabibanik.hospitalManagementSystem.type.BloodGroupType;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Pageable;
import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepo patientRepo;

    @Test
    public void getPatient()
    {
        System.out.println(patientRepo.findAll());
    }

    @Test
    public void getPatientByBloodGroup(){
        List<Patient> patients = patientRepo.findAllPatientWithBloodGroup(BloodGroupType.AB_POSITIVE);

        System.out.println(patients);
    }

    @Test
    public void getAllPatient(){
        System.out.println(patientRepo.findAllPatient());
    }
}
