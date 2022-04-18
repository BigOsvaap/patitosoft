package com.encora.patitosoft.repositories.custom;

import com.encora.patitosoft.dto.SearchAdminEmployeeInfo;
import com.encora.patitosoft.dto.SearchNormalEmployeeInfo;

import java.util.List;

public interface SearchRepositoryCustom {

    List<SearchNormalEmployeeInfo> normalSearchEmployee(String firstName, String lastName, String position);

    List<SearchAdminEmployeeInfo> adminSearchEmployee(String firstName, String lastName, String position, Boolean isDeleted);

}
