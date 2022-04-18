package com.encora.patitosoft.mappers;

import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.entities.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "gender", source = "gender.name")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.street", source = "street")
    @Mapping(target = "address.zipCode", source = "zipCode")
    @Mapping(target = "address.countryCode", source = "country.code")
    @Mapping(target = "address.state", source = "state.name")
    NormalEmployeeInfo employeeEntityToNormalEmployeeInfo(Employee employee);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "country", ignore = true)
    Employee NormalEmployeeInfoToEmployeeEntity(NormalEmployeeInfo normalEmployeeInfo);

    @Mapping(target = "gender", source = "gender.name")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.street", source = "street")
    @Mapping(target = "address.zipCode", source = "zipCode")
    @Mapping(target = "address.countryCode", source = "country.code")
    @Mapping(target = "address.state", source = "state.name")
    @Mapping(target = "history", ignore = true)
    AdminEmployeeInfo employeeEntityToAdminEmployeeInfo(Employee employee);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "country", ignore = true)
    Employee AdminEmployeeInfoToEmployeeEntity(AdminEmployeeInfo adminEmployeeInfo);

}
