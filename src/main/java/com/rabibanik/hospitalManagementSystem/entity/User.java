package com.rabibanik.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Indexed;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String username;      //this will do the job for getUsername method
    @Column(nullable = false)
    private String password;      //this will do the job for getPassword method

    private Set<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
