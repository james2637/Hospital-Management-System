package com.rabibanik.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;

//    @ManyToOne(cascade = CascadeType.PERSIST)   // not here specify in patient otherwise when u delete the appointment it tries to delete the patient also
    @ManyToOne
    @JoinColumn(nullable = false)
    private Patient patient;


}
