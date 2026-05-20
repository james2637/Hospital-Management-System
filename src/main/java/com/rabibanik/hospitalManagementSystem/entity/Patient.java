package com.rabibanik.hospitalManagementSystem.entity;

import com.rabibanik.hospitalManagementSystem.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_constraint_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_constraint_phone", columnNames = {"phone"})
        }
)
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;

    private String email;
    private String phone;
    private String gender;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)  //orphanRemoval this will remove the insuance from insurance table also
    private Insurance insurance;    // FK for insurance

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodGroup=" + bloodGroup +
                ", createdAt=" + createdAt +
                "} \n";
    }
}
