package com.rabibanik.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phone;

    @Column(length = 100)
    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
