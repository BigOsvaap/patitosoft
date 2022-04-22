package com.encora.patitosoft.services;

import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.entities.Employee;
import com.encora.patitosoft.exceptions.InvalidInputException;
import com.encora.patitosoft.exceptions.NotFoundException;
import com.encora.patitosoft.mappers.EmployeeMapper;
import com.encora.patitosoft.repositories.CountryRepository;
import com.encora.patitosoft.repositories.EmployeePositionRepository;
import com.encora.patitosoft.repositories.EmployeeRepository;
import com.encora.patitosoft.repositories.GenderRepository;
import com.encora.patitosoft.repositories.StateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.encora.patitosoft.Utils.createAdminEmployeeInfo;
import static com.encora.patitosoft.Utils.createEmployeeEntity;
import static com.encora.patitosoft.Utils.createNormalEmployeeInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock private EmployeeRepository employeeRepository;
    @Mock private GenderRepository genderRepository;
    @Mock private CountryRepository countryRepository;
    @Mock private StateRepository stateRepository;
    @Mock private EmployeePositionRepository employeePositionRepository;
    @Mock private EmployeeMapper mapper;

    @InjectMocks
    private EmployeeService service;

    @Test
    public void saveEmployeeSuccessful() {
        //Arrange
        NormalEmployeeInfo nei = createNormalEmployeeInfo();
        Employee e = createEmployeeEntity();

        Mockito.when(mapper.normalEmployeeInfoToEmployeeEntity(nei)).thenReturn(e);
        Mockito.when(employeeRepository.findByCorporateEmail(nei.getCorporateEmail())).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findByCode(nei.getAddress().getCountryCode())).thenReturn(Optional.of(e.getCountry()));
        Mockito.when(genderRepository.findByName(nei.getGender())).thenReturn(Optional.of(e.getGender()));
        Mockito.when(stateRepository.findByName(nei.getAddress().getState())).thenReturn(Optional.of(e.getState()));
        Mockito.when(employeeRepository.save(e)).thenReturn(e);
        Mockito.when(mapper.employeeEntityToNormalEmployeeInfo(e)).thenReturn(nei);

        //Act
        NormalEmployeeInfo normalEmployeeInfo = service.saveEmployee(nei);

        //Assert
        assertNotNull(normalEmployeeInfo);
        assertEquals(nei, normalEmployeeInfo);
    }

    @Test
    public void saveEmployeeThrowsInvalidInputExceptionWhenCorporateEmailAlreadyExists() {
        //Arrange
        NormalEmployeeInfo nei = createNormalEmployeeInfo();
        Employee e = createEmployeeEntity();

        Mockito.when(mapper.normalEmployeeInfoToEmployeeEntity(nei)).thenReturn(e);
        Mockito.when(employeeRepository.findByCorporateEmail(nei.getCorporateEmail())).thenReturn(Optional.of(e));

        //Act Assert
        assertThrows(InvalidInputException.class, () -> service.saveEmployee(nei));
    }

    @Test
    public void saveEmployeeThrowsNotFoundWhenCountryCodeDoesntExists() {
        //Arrange
        NormalEmployeeInfo nei = createNormalEmployeeInfo();
        Employee e = createEmployeeEntity();

        Mockito.when(mapper.normalEmployeeInfoToEmployeeEntity(nei)).thenReturn(e);
        Mockito.when(employeeRepository.findByCorporateEmail(nei.getCorporateEmail())).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findByCode(nei.getAddress().getCountryCode())).thenReturn(Optional.empty());

        //Act Assert
        assertThrows(NotFoundException.class, () -> service.saveEmployee(nei));
    }

    @Test
    public void saveEmployeeThrowsNotFoundWhenGenderDoesntExists() {
        //Arrange
        NormalEmployeeInfo nei = createNormalEmployeeInfo();
        Employee e = createEmployeeEntity();

        Mockito.when(mapper.normalEmployeeInfoToEmployeeEntity(nei)).thenReturn(e);
        Mockito.when(employeeRepository.findByCorporateEmail(nei.getCorporateEmail())).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findByCode(nei.getAddress().getCountryCode())).thenReturn(Optional.of(e.getCountry()));
        Mockito.when(genderRepository.findByName(nei.getGender())).thenReturn(Optional.empty());

        //Act Assert
        assertThrows(NotFoundException.class, () -> service.saveEmployee(nei));
    }

    @Test
    public void employeeNormalInfoByCorporateEmail() {
        //Arrange
        String email = "isela.aparicio@patitosoft.com";
        Employee employee = createEmployeeEntity();

        Mockito.when(employeeRepository.findByCorporateEmail(email)).thenReturn(Optional.of(employee));
        Mockito.when(mapper.employeeEntityToNormalEmployeeInfo(employee)).thenReturn(createNormalEmployeeInfo());

        //Act
        NormalEmployeeInfo employeeInfo = service.employeeNormalInfoByCorporateEmail(email);

        //Assert
        assertNotNull(employeeInfo);
    }

    @Test
    public void employeeNormalInfoByCorporateEmailWhenCorporateEmailDoesntExists() {
        //Arrange
        String email = "isela.aparacio@patitosoft.com";
        Mockito.when(employeeRepository.findByCorporateEmail(email)).thenReturn(Optional.empty());

        //Act Assert
        assertThrows(NotFoundException.class, () -> service.employeeNormalInfoByCorporateEmail(email));
    }

    @Test
    public void employeeAdminInfoByCorporateEmail() {
        //Arrange
        String email = "isela.aparacio@patitosoft.com";
        Employee employee = createEmployeeEntity();

        Mockito.when(employeeRepository.findByCorporateEmail(email)).thenReturn(Optional.of(employee));
        Mockito.when(employeePositionRepository.getEmployeeHistoryOfPositions(email)).thenReturn(new ArrayList<>());
        Mockito.when(mapper.employeeEntityToAdminEmployeeInfo(employee)).thenReturn(createAdminEmployeeInfo());

        //Act
        AdminEmployeeInfo adminEmployeeInfo = service.employeeAdminInfoByCorporateEmail(email);

        //Assertions
        assertNotNull(adminEmployeeInfo);
    }

    @Test
    public void employeeAdminInfoByCorporateEmailWhenCorporateEmailDoesntExists() {
        //Arrange
        String email = "isela.aparacio@patitosoft.com";
        Mockito.when(employeeRepository.findByCorporateEmail(email)).thenReturn(Optional.empty());

        //Act Assert
        assertThrows(NotFoundException.class, () -> service.employeeAdminInfoByCorporateEmail(email));
    }

}