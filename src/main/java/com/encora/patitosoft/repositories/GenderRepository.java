package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.Gender;
import com.encora.patitosoft.repositories.projections.EmployeesByGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenderRepository extends JpaRepository<Gender, UUID> {

    @Query(
            value = """
                SELECT
                    name AS gender,
                    COUNT(employee_id) AS totalEmployees
                FROM gender
                LEFT JOIN employee USING (gender_id)
                WHERE is_deleted = FALSE
                GROUP BY name
            """,
            nativeQuery = true
    )
    List<EmployeesByGender> countEmployeesByGender();

    Optional<Gender> findByName(String name);

}
