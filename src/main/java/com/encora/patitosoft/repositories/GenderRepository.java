package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenderRepository extends JpaRepository<Gender, UUID> {

}
