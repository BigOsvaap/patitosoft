package com.encora.patitosoft;

import com.encora.patitosoft.dto.AddressInfo;
import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.entities.Country;
import com.encora.patitosoft.entities.Employee;
import com.encora.patitosoft.entities.Gender;
import com.encora.patitosoft.entities.State;

import java.time.LocalDate;

public class Utils {

    public static Employee createEmployeeEntity() {
        Employee e = new Employee();
        e.setCorporateEmail("isela.aparicio@patitosoft.com");
        e.setFirstName("Isela");
        e.setLastName("Aparicio");
        e.setPersonalEmail("isela.aparacio@gmail.com");
        e.setPhoneNumber("23424233333");
        e.setCity("Xalapa");
        e.setStreet("Inventada 23123");
        e.setZipCode("93820");
        e.setBirthDay(LocalDate.parse("2000-03-22"));
        e.setIsDeleted(false);

        Gender g = new Gender();
        g.setName("Female");

        State s = new State();
        s.setName("VERACRUZ");

        Country c = new Country();
        c.setCode("MX");

        e.setGender(g);
        e.setState(s);
        e.setCountry(c);

        return e;
    }

    public static NormalEmployeeInfo createNormalEmployeeInfo() {
        NormalEmployeeInfo e = new NormalEmployeeInfo();
        e.setCorporateEmail("isela.aparicio@patitosoft.com");
        e.setFirstName("Isela");
        e.setLastName("Aparicio");
        e.setPersonalEmail("isela.aparacio@gmail.com");
        e.setPhoneNumber("23424233333");
        e.setBirthDay(LocalDate.parse("2000-03-22"));
        e.setGender("Female");

        AddressInfo a = new AddressInfo();
        a.setCity("Xalapa");
        a.setStreet("Inventada 23123");
        a.setZipCode("93820");
        a.setCountryCode("MX");
        a.setState("VERACRUZ");

        e.setAddress(a);

        return e;
    }

    public static AdminEmployeeInfo createAdminEmployeeInfo() {
        AdminEmployeeInfo e = new AdminEmployeeInfo();
        e.setCorporateEmail("isela.aparicio@patitosoft.com");
        e.setFirstName("Isela");
        e.setLastName("Aparicio");
        e.setPersonalEmail("isela.aparacio@gmail.com");
        e.setPhoneNumber("23424233333");
        e.setBirthDay(LocalDate.parse("2000-03-22"));
        e.setGender("Female");

        AddressInfo a = new AddressInfo();
        a.setCity("Xalapa");
        a.setStreet("Inventada 23123");
        a.setZipCode("93820");
        a.setCountryCode("MX");
        a.setState("VERACRUZ");

        e.setAddress(a);

        return e;
    }

}
