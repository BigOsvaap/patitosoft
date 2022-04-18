package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.EmployeePosition;
import com.encora.patitosoft.repositories.projections.EmployeePositionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, UUID> {

    @Query(value = """
            SELECT
                name AS positionName,
                salary AS salary,
                date AS date
            FROM employee_position
            LEFT JOIN position USING (position_id)
            LEFT JOIN  employee USING (employee_id)
            WHERE corporate_email = ? AND is_deleted = FALSE
            """,
            nativeQuery = true
    )
    List<EmployeePositionsHistory> getEmployeeHistoryOfPositions(String corporateEmail);

}
