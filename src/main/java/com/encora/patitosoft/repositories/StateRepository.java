package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

    Optional<State> findByName(String name);

}
