package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.Country;
import com.encora.patitosoft.repositories.projections.EmployeeByState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    @Query(
            value = """
                    SELECT
                        name AS stateName,
                        COUNT(employee_id) AS totalEmployees
                    FROM employee
                    LEFT JOIN state USING(state_id, country_id)
                    LEFT JOIN country USING (country_id)
                    WHERE code = ? AND is_deleted = FALSE
                    GROUP BY name
                    """,
            nativeQuery = true
    )
    List<EmployeeByState> countEmployeeByCountryStates(String countryCode);

    Optional<Country> findByCode(String code);

}
