package com.encora.patitosoft.repositories.projections;

import java.time.LocalDateTime;

public interface EmployeePositionsHistory {

    String getPositionName();
    Double getSalary();
    LocalDateTime getDate();

}
