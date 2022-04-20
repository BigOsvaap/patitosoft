package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.Position;
import com.encora.patitosoft.repositories.projections.EmployeeBetweenRange;
import com.encora.patitosoft.repositories.projections.EmployeesByPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {

    @Query(
            value = """
                    SELECT
                        name AS position,
                        COUNT(employee_id) AS totalEmployees
                    FROM
                        (SELECT DISTINCT ON(employee_id) employee_id, position_id, date FROM employee_position ORDER BY employee_id, date DESC) as employeesCurrentPosition
                    LEFT JOIN position USING (position_id)
                    LEFT JOIN employee USING (employee_id)
                    WHERE is_deleted = FALSE
                    GROUP BY name
                    """,
            nativeQuery = true
    )
    List<EmployeesByPosition> countEmployeesByPosition();

    Optional<Position> findPositionByName(String name);

    @Query(
            value = """
                    SELECT
                        MAX(salary)
                    FROM (SELECT
                              DISTINCT employee_id,
                                       date,
                                       salary
                          FROM employee_position
                                   LEFT JOIN employee USING (employee_id)
                          WHERE is_deleted = FALSE
                          ORDER BY date DESC) AS currentSalaries
                    """,
            nativeQuery = true
    )
    Double currentMaxSalary();

    @Query(
            value = """
                    SELECT
                        MIN(salary)
                    FROM (SELECT
                              DISTINCT employee_id,
                                       date,
                                       salary
                          FROM employee_position
                                   LEFT JOIN employee USING (employee_id)
                          WHERE is_deleted = FALSE
                          ORDER BY date DESC) AS currentSalaries
                    """,
            nativeQuery = true
    )
    Double currentMinSalary();

    @Query(
            value = """
                    SELECT
                        :greaterThanOrEqual AS greaterThanOrEqual,
                        :smallerThan AS smallerThan,
                        totalEmployees AS totalEmployeesBetweenRange
                    FROM (SELECT
                                salary >= :greaterThanOrEqual AND salary < :smallerThan AS range,
                                COUNT(currentSalaries.employee_id) AS totalEmployees
                          FROM (SELECT DISTINCT ON(employee_id)
                                                employee_id,
                                                date,
                                                salary
                                FROM employee_position
                                LEFT JOIN employee USING (employee_id)
                                WHERE is_deleted = FALSE
                                ORDER BY employee_id, date DESC) AS currentSalaries
                          GROUP BY range) AS employeesBetweenRange
                    WHERE range = TRUE
                    """,
            nativeQuery = true
    )
    EmployeeBetweenRange getEmployeesBetweenSalaryRange(@Param("greaterThanOrEqual") Double greaterThanOrEqual, @Param("smallerThan") Double smallerThan);


}
