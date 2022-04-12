-- Bridge table between employee and position table

CREATE TABLE employee_position(
    employee_position_id UUID DEFAULT uuid_generate_v4(),
    salary NUMERIC(12, 2) NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    employee_id UUID NOT NULL,
    position_id UUID NOT NULL,
    PRIMARY KEY (employee_position_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (position_id) REFERENCES position(position_id)
);

INSERT INTO
    employee_position (salary, date, employee_id, position_id)
VALUES
    (30000.00, '2022-02-21', (SELECT employee_id FROM employee WHERE corporate_email = 'oswaldo.vazquez@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer')),
    (50000.00, '2022-01-15', (SELECT employee_id FROM employee WHERE corporate_email = 'arturo.miranda@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Talent Acquisition')),
    (60000.00, '2021-12-25', (SELECT employee_id FROM employee WHERE corporate_email = 'naomi.ceron@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Lawyer')),
    (40000.00, '2022-03-22', (SELECT employee_id FROM employee WHERE corporate_email = 'laura.iranda@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Accountant')),
    (35000.00, '2022-04-12', (SELECT employee_id FROM employee WHERE corporate_email = 'carlos.cuevas@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer')),
    (80000.00, '2000-05-15', (SELECT employee_id FROM employee WHERE corporate_email = 'george.smith@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer')),
    (45000.00, '2010-03-22', (SELECT employee_id FROM employee WHERE corporate_email = 'jada.winchester@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Talent Acquisition')),
    (55000.00, '2012-04-22', (SELECT employee_id FROM employee WHERE corporate_email = 'michael.jackson@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Lawyer')),
    (100000.00, '1999-12-31', (SELECT employee_id FROM employee WHERE corporate_email = 'ada.wong@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Accountant')),
    (75000.00, '2022-01-01', (SELECT employee_id FROM employee WHERE corporate_email = 'jennifer.olivia@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer')),
    (95000.00, '2017-05-07', (SELECT employee_id FROM employee WHERE corporate_email = 'rafael.marcelo@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer')),
    (55000.00, '2015-07-08', (SELECT employee_id FROM employee WHERE corporate_email = 'pedro.herrera@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Talent Acquisition')),
    (47000.00, '2011-11-11', (SELECT employee_id FROM employee WHERE corporate_email = 'rafaela.marcela@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Lawyer')),
    (85000.00, '2007-07-07', (SELECT employee_id FROM employee WHERE corporate_email = 'samuel.arellano@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Accountant')),
    (77777.00, '2012-12-12', (SELECT employee_id FROM employee WHERE corporate_email = 'jesus.lerdo@patitosoft.com'), (SELECT position_id FROM position WHERE name = 'Software Engineer'));