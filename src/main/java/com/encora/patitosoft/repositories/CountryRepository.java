package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

}
