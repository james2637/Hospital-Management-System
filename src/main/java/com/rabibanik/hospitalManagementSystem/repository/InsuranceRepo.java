package com.rabibanik.hospitalManagementSystem.repository;

import com.rabibanik.hospitalManagementSystem.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepo extends JpaRepository<Insurance, Long> {
}
