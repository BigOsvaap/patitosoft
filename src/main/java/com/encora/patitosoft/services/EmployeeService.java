package com.encora.patitosoft.services;

import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.entities.Country;
import com.encora.patitosoft.entities.Employee;
import com.encora.patitosoft.entities.EmployeePosition;
import com.encora.patitosoft.entities.Gender;
import com.encora.patitosoft.entities.Position;
import com.encora.patitosoft.entities.State;
import com.encora.patitosoft.exceptions.NotFoundException;
import com.encora.patitosoft.mappers.EmployeeMapper;
import com.encora.patitosoft.repositories.CountryRepository;
import com.encora.patitosoft.repositories.EmployeePositionRepository;
import com.encora.patitosoft.repositories.EmployeeRepository;
import com.encora.patitosoft.repositories.GenderRepository;
import com.encora.patitosoft.repositories.PositionRepository;
import com.encora.patitosoft.repositories.StateRepository;
import com.encora.patitosoft.repositories.projections.AdminEmployeeSearchInfo;
import com.encora.patitosoft.repositories.projections.EmployeePositionsHistory;
import com.encora.patitosoft.repositories.projections.NormalEmployeeSearchInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeePositionRepository employeePositionRepository;

    private final GenderRepository genderRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final PositionRepository positionRepository;

    private final EmployeeMapper mapper;


    public NormalEmployeeInfo saveEmployee(NormalEmployeeInfo normalEmployeeInfo) {
        Employee employeeToSave = mapper.NormalEmployeeInfoToEmployeeEntity(normalEmployeeInfo);

        Optional<Country> country = countryRepository.findByCode(normalEmployeeInfo.getAddress().getCountryCode());
        country.orElseThrow(() -> new NotFoundException("Country code doesn't exist"));
        employeeToSave.setCountry(country.get());

        Optional<Gender> gender = genderRepository.findByName(normalEmployeeInfo.getGender());
        gender.orElseThrow(() -> new NotFoundException("Gender doesn't exist"));
        employeeToSave.setGender(gender.get());

        Optional<State> state = stateRepository.findByName(normalEmployeeInfo.getAddress().getState());
        state.orElseThrow(() -> new NotFoundException("State doesn't exist"));
        employeeToSave.setState(state.get());

        return mapper.employeeEntityToNormalEmployeeInfo(employeeRepository.save(employeeToSave));
    }

    public void assignPositionToEmployee(String corporateEmail, String positionName, Double salary) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCorporateEmail(corporateEmail);
        optionalEmployee.orElseThrow(() -> new NotFoundException("Employee not found"));

        if (optionalEmployee.get().getIsDeleted()) {
            throw new NotFoundException("Employee not found");
        }

        Optional<Position> position = positionRepository.findPositionByName(positionName);
        position.orElseThrow(() -> new NotFoundException("Position doesn't exist"));

        employeePositionRepository.save(new EmployeePosition(optionalEmployee.get(), position.get(), salary));
    }

    public Page<NormalEmployeeSearchInfo> normalEmployeeSearchInfo(String firstName, String lastName, String position, int page, int size) {
        return employeeRepository.normalEmployeeSearch(firstName, lastName, position, PageRequest.of(page, size));
    }

    public Page<AdminEmployeeSearchInfo> adminEmployeeSearchInfo(String firstName, String lastName, String position, Boolean isDeleted, int page, int size) {
        return employeeRepository.adminEmployeeSearch(firstName, lastName, position, isDeleted, PageRequest.of(page, size));
    }

    public NormalEmployeeInfo employeeNormalInfoByCorporateEmail(String corporateEmail) {
        Optional<Employee> employee = employeeRepository.findByCorporateEmail(corporateEmail);
        employee.orElseThrow(() -> new NotFoundException("Employee not found"));
        if (employee.get().getIsDeleted()) {
            throw new NotFoundException("Employee not found");
        }
        return mapper.employeeEntityToNormalEmployeeInfo(employee.get());
    }

    public AdminEmployeeInfo employeeAdminInfoByCorporateEmail(String corporateEmail) {
        Optional<Employee> employee = employeeRepository.findByCorporateEmail(corporateEmail);
        employee.orElseThrow(() -> new NotFoundException("Employee not found"));

        List<EmployeePositionsHistory> employeeHistory = employeePositionRepository.getEmployeeHistoryOfPositions(corporateEmail);
        AdminEmployeeInfo adminEmployeeInfo = mapper.employeeEntityToAdminEmployeeInfo(employee.get());
        adminEmployeeInfo.setHistory(employeeHistory);

        return adminEmployeeInfo;
    }

    public List<String> whoIsBirthdayTodayEmails() {
        return employeeRepository.whoIsBirthdayToday();
    }

    public List<String> whoIsBirthdayNextWeekEmails() {
        return employeeRepository.whoIsBirthdayNextWeek();
    }

    @Transactional
    public void softDeleteEmployeeByCorporateEmail(String corporateEmail) {
        employeeRepository.deleteEmployeeByCorporateEmail(corporateEmail);
    }

}
