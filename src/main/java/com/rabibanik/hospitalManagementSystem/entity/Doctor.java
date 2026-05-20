package com.rabibanik.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_constraint_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_constraint_phone", columnNames = {"phone"})
        }
)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Name;

    private String email;
    private String phone;

    @Column(length = 100)
    private String specialization;

}
