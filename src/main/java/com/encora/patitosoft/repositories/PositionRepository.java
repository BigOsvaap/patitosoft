package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {

}
