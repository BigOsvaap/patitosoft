package com.encora.patitosoft.repositories;

import com.encora.patitosoft.entities.Employee;
import com.encora.patitosoft.repositories.custom.SearchRepositoryCustom;
import com.encora.patitosoft.repositories.projections.AdminEmployeeSearchInfo;
import com.encora.patitosoft.repositories.projections.NormalEmployeeSearchInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>, SearchRepositoryCustom {

    @Query(
            value = """
                    SELECT
                        corporate_email AS corporateEmail,
                        first_name AS firstName,
                        last_name AS lastName,
                        name AS positionName
                    FROM employee
                    LEFT JOIN employee_position USING (employee_id)
                    LEFT JOIN position USING (position_id)
                    WHERE is_deleted = FALSE AND
                          first_name ILIKE '%' || :firstName || '%' AND
                          last_name ILIKE '%' || :lastName || '%' AND
                          name ILIKE '%' || :position || '%'
                    """,
            countQuery = """
                    SELECT
                        COUNT(*)
                    FROM (
                        SELECT
                            corporate_email
                        FROM employee
                        LEFT JOIN employee_position USING (employee_id)
                        LEFT JOIN position USING (position_id)
                        WHERE is_deleted = FALSE AND
                              first_name ILIKE '%' || :firstName || '%' AND
                              last_name ILIKE '%' || :lastName || '%' AND
                              name ILIKE '%' || :position || '%'
                        ) AS countTable
            """,
            nativeQuery = true
    )
    Page<NormalEmployeeSearchInfo> normalEmployeeSearch(@Param("firstName") String firstName,
                                                        @Param("lastName") String lastName,
                                                        @Param("position") String position,
                                                        Pageable pageable);

    @Query(
            value = """
                    SELECT
                        corporate_email AS corporateEmail,
                        first_name AS firstName,
                        last_name AS lastName,
                        name AS positionName,
                        is_deleted AS isDeleted
                    FROM employee
                    LEFT JOIN employee_position USING (employee_id)
                    LEFT JOIN position USING (position_id)
                    WHERE is_deleted = :isDeleted AND
                          first_name ILIKE '%' || :firstName || '%' AND
                          last_name ILIKE '%' || :lastName || '%' AND
                          name ILIKE '%' || :position || '%'
                    """
            ,
            countQuery = """
                    SELECT
                        COUNT(*)
                    FROM (
                            SELECT
                                corporate_email
                            FROM employee
                            LEFT JOIN employee_position USING (employee_id)
                            LEFT JOIN position USING (position_id)
                            WHERE is_deleted = :isDeleted AND
                                  first_name ILIKE '%' || :firstName || '%' AND
                                  last_name ILIKE '%' || :lastName || '%' AND
                                  name ILIKE '%' || :position || '%'
                    ) AS countTable
                    """,
            nativeQuery = true
    )
    Page<AdminEmployeeSearchInfo> adminEmployeeSearch(@Param("firstName") String firstName,
                                                      @Param("lastName") String lastName,
                                                      @Param("position") String position,
                                                      @Param("isDeleted") Boolean isDeleted,
                                                      Pageable pageable);


    Optional<Employee> findByCorporateEmail(String corporateEmail);

    @Query(
            value = """
                    SELECT
                        corporate_email
                    FROM employee
                    WHERE DATE_PART('day', birth_day) = DATE_PART('day', CURRENT_DATE) AND
                          DATE_PART('month', birth_day) = DATE_PART('month', CURRENT_DATE) AND
                          is_deleted = FALSE
                          """,
            nativeQuery = true
    )
    List<String> whoIsBirthdayToday();

    @Query(
            value = """
                    SELECT
                        corporate_email
                    FROM employee
                    WHERE EXTRACT(WEEK FROM CURRENT_DATE) + 1 = EXTRACT(WEEK FROM birth_day) AND
                          is_deleted = FALSE
                          """,
            nativeQuery = true
    )
    List<String> whoIsBirthdayNextWeek();

    @Modifying
    @Query(
            value = """
                    UPDATE employee
                    SET is_deleted = TRUE
                    WHERE corporate_email = ?
                    """,
            nativeQuery = true
    )
    Integer deleteEmployeeByCorporateEmail(String corporateEmail);

}
