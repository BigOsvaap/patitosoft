-- Bridge table between employee and position table

CREATE TABLE employee_position(
    id UUID DEFAULT uuid_generate_v4(),
    salary NUMERIC(12, 2) NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    employee_id UUID NOT NULL,
    position_id UUID NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (position_id) REFERENCES position(id)
);

INSERT INTO
    employee_position (salary, date, employee_id, position_id)
VALUES
    (30000.00, '2022-02-21', (SELECT id FROM employee WHERE corporate_email = 'oswaldo.vazquez@patitosoft.com'), (SELECT id FROM position WHERE name = 'Software Engineer')),
    (50000.00, '2022-01-15', (SELECT id FROM employee WHERE corporate_email = 'arturo.miranda@patitosoft.com'), (SELECT id FROM position WHERE name = 'Talent Acquisition')),
    (60000.00, '2021-12-25', (SELECT id FROM employee WHERE corporate_email = 'naomi.ceron@patitosoft.com'), (SELECT id FROM position WHERE name = 'Lawyer'));