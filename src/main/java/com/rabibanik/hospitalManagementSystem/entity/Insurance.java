package com.rabibanik.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
