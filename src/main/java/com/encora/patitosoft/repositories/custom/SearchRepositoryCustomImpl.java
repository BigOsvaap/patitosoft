package com.encora.patitosoft.repositories.custom;

import com.encora.patitosoft.dto.SearchAdminEmployeeInfo;
import com.encora.patitosoft.dto.SearchNormalEmployeeInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SearchNormalEmployeeInfo> normalSearchEmployee(String firstName, String lastName, String position) {
        String query = """
                SELECT
                    corporate_email AS corporateEmail,
                    first_name AS firstName,
                    last_name AS lastName,
                    name AS positionName
                FROM employee
                LEFT JOIN employee_position USING (employee_id)
                LEFT JOIN position USING (position_id)
                WHERE is_deleted = FALSE""";

        List<String> predicates = new ArrayList<>(3);
        if (!Objects.requireNonNull(firstName).isEmpty()) {
            predicates.add(" first_name ILIKE '%' || '"+firstName+"' || '%' ");
        }
        if (!Objects.requireNonNull(lastName).isEmpty()) {
            predicates.add(" last_name ILIKE '%' || '"+lastName+"' || '%' ");
        }
        if (!Objects.requireNonNull(position).isEmpty()) {
            predicates.add(" name ILIKE '%' || '"+position+"' || '%' ");
        }

        if (!predicates.isEmpty()) {
            query += String.join("AND", predicates);
        }

        Query nativeQuery = entityManager.createNativeQuery(query);

        List<Object[]> resultList = nativeQuery.getResultList();

        return resultList.stream()
                .map(object -> new SearchNormalEmployeeInfo((String) object[0], (String) object[1], (String) object[2], (String) object[3]))
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchAdminEmployeeInfo> adminSearchEmployee(String firstName, String lastName, String position, Boolean isDeleted) {
        String query = """
                SELECT
                    corporate_email AS corporateEmail,
                    first_name AS firstName,
                    last_name AS lastName,
                    name AS positionName,
                    is_deleted AS isDeleted
                FROM employee
                LEFT JOIN employee_position USING (employee_id)
                LEFT JOIN position USING (position_id)
                WHERE""";

        List<String> predicates = new ArrayList<>(3);

        if (!Objects.isNull(isDeleted)) {
            predicates.add(" is_deleted = "+isDeleted);
        }
        if (!Objects.requireNonNull(firstName).isEmpty()) {
            predicates.add(" first_name ILIKE '%' || '"+firstName+"' || '%' ");
        }
        if (!Objects.requireNonNull(lastName).isEmpty()) {
            predicates.add(" last_name ILIKE '%' || '"+lastName+"' || '%' ");
        }
        if (!Objects.requireNonNull(position).isEmpty()) {
            predicates.add(" name ILIKE '%' || '"+position+"' || '%' ");
        }

        if (!predicates.isEmpty()) {
            query += String.join("AND", predicates);
        }

        Query nativeQuery = entityManager.createNativeQuery(query);

        List<Object[]> resultList = nativeQuery.getResultList();

        return resultList.stream()
                .map(object -> new SearchAdminEmployeeInfo((String) object[0], (String) object[1], (String) object[2], (String) object[3], (Boolean) object[4]))
                .collect(Collectors.toList());
    }
}
