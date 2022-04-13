package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

}
