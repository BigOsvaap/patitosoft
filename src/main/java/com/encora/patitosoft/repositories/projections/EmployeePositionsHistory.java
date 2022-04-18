package com.encora.patitosoft.repositories.projections;

import java.sql.Date;

public interface EmployeePositionsHistory {

    String getPositionName();
    Double getSalary();
    Date getDate();

}
