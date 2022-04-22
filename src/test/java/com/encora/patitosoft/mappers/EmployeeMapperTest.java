package com.encora.patitosoft.mappers;

import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.entities.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.encora.patitosoft.Utils.createEmployeeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class EmployeeMapperTest {

    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeeEntityToNormalEmployeeInfo() {
        Employee e = createEmployeeEntity();

        NormalEmployeeInfo nei = mapper.employeeEntityToNormalEmployeeInfo(e);

        assertEquals(e.getCorporateEmail(), nei.getCorporateEmail());
        assertEquals(e.getFirstName(), nei.getFirstName());
        assertEquals(e.getLastName(), nei.getLastName());
        assertEquals(e.getPersonalEmail(), nei.getPersonalEmail());
        assertEquals(e.getPhoneNumber(), nei.getPhoneNumber());
        assertEquals(e.getCity(), nei.getAddress().getCity());
        assertEquals(e.getStreet(), nei.getAddress().getStreet());
        assertEquals(e.getZipCode(), nei.getAddress().getZipCode());
        assertEquals(e.getCountry().getCode(), nei.getAddress().getCountryCode());
        assertEquals(e.getState().getName(), nei.getAddress().getState());
        assertEquals(e.getBirthDay(), nei.getBirthDay());
        assertEquals(e.getGender().getName(), nei.getGender());
    }

    @Test
    void normalEmployeeInfoToEmployeeEntity() {
        Employee e = createEmployeeEntity();
        NormalEmployeeInfo nei = mapper.employeeEntityToNormalEmployeeInfo(e);

        Employee emp = mapper.normalEmployeeInfoToEmployeeEntity(nei);

        assertEquals(emp.getCorporateEmail(), nei.getCorporateEmail());
        assertEquals(emp.getFirstName(), nei.getFirstName());
        assertEquals(emp.getLastName(), nei.getLastName());
        assertEquals(emp.getPersonalEmail(), nei.getPersonalEmail());
        assertEquals(emp.getPhoneNumber(), nei.getPhoneNumber());
        assertEquals(emp.getCity(), nei.getAddress().getCity());
        assertEquals(emp.getStreet(), nei.getAddress().getStreet());
        assertEquals(emp.getZipCode(), nei.getAddress().getZipCode());
        assertEquals(emp.getBirthDay(), nei.getBirthDay());

        assertNull(emp.getId());
        assertNull(emp.getCountry());
        assertNull(emp.getState());
        assertNull(emp.getState());
    }

    @Test
    void employeeEntityToAdminEmployeeInfo() {

        Employee e = createEmployeeEntity();

        AdminEmployeeInfo aei = mapper.employeeEntityToAdminEmployeeInfo(e);

        assertEquals(e.getCorporateEmail(), aei.getCorporateEmail());
        assertEquals(e.getFirstName(), aei.getFirstName());
        assertEquals(e.getLastName(), aei.getLastName());
        assertEquals(e.getPersonalEmail(), aei.getPersonalEmail());
        assertEquals(e.getPhoneNumber(), aei.getPhoneNumber());
        assertEquals(e.getCity(), aei.getAddress().getCity());
        assertEquals(e.getStreet(), aei.getAddress().getStreet());
        assertEquals(e.getZipCode(), aei.getAddress().getZipCode());
        assertEquals(e.getCountry().getCode(), aei.getAddress().getCountryCode());
        assertEquals(e.getState().getName(), aei.getAddress().getState());
        assertEquals(e.getBirthDay(), aei.getBirthDay());
        assertEquals(e.getGender().getName(), aei.getGender());

        assertNull(aei.getHistory());

    }



}