package com.rabibanik.hospitalManagementSystem.controller;


import com.rabibanik.hospitalManagementSystem.entity.Insurance;
import com.rabibanik.hospitalManagementSystem.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/add/{id}")
    public void addInsuranceToPatient(@PathVariable("id") long patientId,@RequestBody Insurance insurance){
        insuranceService.addPatientInsurance(patientId, insurance);
    }

}
