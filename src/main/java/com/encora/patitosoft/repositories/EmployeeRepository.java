package com.encora.patitosoft.repositories;

import com.encora.patitosoft.models.Employee;
import com.encora.patitosoft.repositories.projections.EmployeesByGender;
import com.encora.patitosoft.repositories.projections.EmployeesByPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(
            value = """
                    SELECT
                        name as position,
                        COUNT(employee_id) as totalEmployees
                    FROM
                        (SELECT DISTINCT employee_id, position_id, date FROM employee_position ORDER BY date DESC) as employeesCurrentPosition
                    LEFT JOIN position USING (position_id)
                    LEFT JOIN employee USING (employee_id)
                    WHERE is_deleted = FALSE
                    GROUP BY name""",
            nativeQuery = true
    )
    List<EmployeesByPosition> countEmployeesInEachPosition();

    @Query(
            value = """
                SELECT
                    name as gender,
                    COUNT(employee_id) as totalEmployees
                FROM gender
                LEFT JOIN employee USING (gender_id)
                WHERE is_deleted = FALSE
                GROUP BY name
            """,
            nativeQuery = true
    )
    List<EmployeesByGender> countEmployeesInEachGender();

    @Modifying
    @Query(
            value = """
                    UPDATE employee
                    SET is_deleted = true
                    WHERE corporate_email = ?""",
            nativeQuery = true
    )
    void deleteEmployeeByCorporateEmail(String corporateEmail);

    @Query(
            value = """
                    SELECT
                        *
                    FROM employee
                    WHERE DATE_PART('day', birth_day) = DATE_PART('day', CURRENT_DATE) AND
                          DATE_PART('month', birth_day) = DATE_PART('month', CURRENT_DATE) AND
                          is_deleted = FALSE""",
            nativeQuery = true
    )
    List<Employee> whoIsBirthdayToday();

    @Query(
            value = """
                    SELECT
                        *
                    FROM employee
                    WHERE EXTRACT(WEEK FROM CURRENT_DATE) + 1 = EXTRACT(WEEK FROM birth_day) AND
                          is_deleted = FALSE""",
            nativeQuery = true
    )
    List<Employee> whoIsBirthdayNextWeek();



}
