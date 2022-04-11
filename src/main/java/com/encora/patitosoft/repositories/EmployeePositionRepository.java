package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, UUID> {

}
