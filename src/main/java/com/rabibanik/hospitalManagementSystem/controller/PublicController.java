package com.rabibanik.hospitalManagementSystem.controller;

import com.rabibanik.hospitalManagementSystem.entity.Doctor;
import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.service.DoctorService;
import com.rabibanik.hospitalManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello from Hospital Management System, API Working";
    }

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.seeAllDoctors();
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){

        userService.createUser(user);
    }

}
