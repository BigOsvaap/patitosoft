-- Employee table

CREATE TABLE employee(
    employee_id UUID DEFAULT uuid_generate_v4(),
    corporate_email VARCHAR(60) UNIQUE NOT NULL,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    personal_email VARCHAR(60) UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    city VARCHAR(60) NOT NULL,
    street VARCHAR(60) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    birth_day DATE,
    is_deleted BOOLEAN DEFAULT FALSE,
    gender_id UUID NOT NULL,
    country_id UUID NOT NULL,
    state_id UUID NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (gender_id) REFERENCES gender(gender_id),
    FOREIGN KEY (country_id) REFERENCES country(country_id),
    FOREIGN KEY (state_id) REFERENCES state(state_id)
);


-- Populating employees
INSERT INTO
    employee (corporate_email, first_name, last_name, personal_email, phone_number, city, street, zip_code, birth_day, gender_id, country_id, state_id)
VALUES
    ('oswaldo.vazquez@patitosoft.com', 'Oswaldo', 'Vazquez', 'bigosvaap@gmail.com', '2351012288', 'Misantla', 'Francisco I Mader 119', '93820', '1996-03-22', (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'MX'), (SELECT state_id FROM state WHERE name = 'VERACRUZ')),
    ('carlos.cuevas@patitosoft.com', 'Carlos', 'Cuevas', 'cuevas@gmail.com', '1232132323', 'Merida', 'Inventada 222', '86954', NULL, (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'MX'), (SELECT state_id FROM state WHERE name = 'YUCATAN')),
    ('laura.iranda@patitosoft.com', 'Laura', 'Iranda', NULL, '1232993822', 'Monterrey', 'Inventada 236', '09321', NULL, (SELECT gender_id FROM gender WHERE name = 'Non binary'), (SELECT country_id FROM country WHERE code = 'MX'), (SELECT state_id FROM state WHERE name = 'NUEVO LEON')),
    ('arturo.miranda@patitosoft.com', 'Arturo', 'Miranda', 'arturo.miranda@gmail.com', '2282123304', 'Hermosillo', 'Inventada 212', '93909', '1996-08-22', (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'MX'), (SELECT state_id FROM state WHERE name = 'SONORA')),
    ('naomi.ceron@patitosoft.com', 'Naomi', 'Ceron', 'naomi.ceron@gmail.com', '2342123355', 'Hermosillo', 'Inventada 231', '93909', '1998-08-22', (SELECT gender_id FROM gender WHERE name = 'Female'), (SELECT country_id FROM country WHERE code = 'MX'), (SELECT state_id FROM state WHERE name = 'SONORA')),
    ('george.smith@patitosoft.com', 'George', 'Smith', 'george@gmail.com', '32423432233', 'Houston', 'Inventada 245', '67432', '1975-04-08', (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'US'), (SELECT state_id FROM state WHERE name = 'TEXAS')),
    ('jada.winchester@patitosoft.com', 'Jada', 'Winchester', 'jada@gmail.com', '18726382716', 'Austin', 'Inventada 356', '65732', '1994-05-01', (SELECT gender_id FROM gender WHERE name = 'I prefer not to say'), (SELECT country_id FROM country WHERE code = 'US'), (SELECT state_id FROM state WHERE name = 'TEXAS')),
    ('michael.jackson@patitosoft.com', 'Michael', 'Jackson', 'michael@gmail.com', '8483984322', 'Phoenix', 'Inventada 968', '99598', '1999-06-02', (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'US'), (SELECT state_id FROM state WHERE name = 'ARIZONA')),
    ('ada.wong@patitosoft.com', 'Ada', 'Wong', NULL, NULL, 'Sacramento', 'Inventada 232221', '95821', NULL, (SELECT gender_id FROM gender WHERE name = 'Female'), (SELECT country_id FROM country WHERE code = 'US'), (SELECT state_id FROM state WHERE name = 'CALIFORNIA')),
    ('jennifer.olivia@patitosoft.com', 'Jennifer', 'Olivia', NULL, NULL, 'Fayetteville', 'Inventada 99688', '95444', NULL, (SELECT gender_id FROM gender WHERE name = 'Female'), (SELECT country_id FROM country WHERE code = 'US'), (SELECT state_id FROM state WHERE name = 'ARKANSAS')),
    ('rafael.marcelo@patitosoft.com', 'Rafael', 'Marcelo', NULL, NULL, 'Xapuri', 'Inventada 7373', '83432', NULL, (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'BR'), (SELECT state_id FROM state WHERE name = 'ACRE')),
    ('pedro.herrera@patitosoft.com', 'Pedro', 'Herrera', NULL, '22232871723', 'Xapuri', 'Inventada 2321', '213121', '2000-04-19', (SELECT gender_id FROM gender WHERE name = 'Male'), (SELECT country_id FROM country WHERE code = 'BR'), (SELECT state_id FROM state WHERE name = 'ACRE')),
    ('rafaela.marcela@patitosoft.com', 'Rafaela', 'Marcela', 'rafaela@gmail.com', '31323123122', 'Porto Velho', 'Inventada 28372', '343532', NULL, (SELECT gender_id FROM gender WHERE name = 'Female'),  (SELECT country_id FROM country WHERE code = 'BR'), (SELECT state_id FROM state WHERE name = 'RONDONIA')),
    ('samuel.arellano@patitosoft.com', 'Samuel', 'Arellano', 'samuel@gmail.com', '23124123121', 'Caxias', 'Inventada 232123', '232123', NULL, (SELECT gender_id FROM gender WHERE name = 'Female'), (SELECT country_id FROM country WHERE code = 'BR'), (SELECT state_id FROM state WHERE name = 'MARANHAO')),
    ('jesus.lerdo@patitosoft.com', 'Jesus', 'Lerdo', NULL, '2332423423', 'Porto Seguro', 'Inventada 23123', '98432', '1990-04-13', (SELECT gender_id FROM gender WHERE name = 'Non binary'), (SELECT country_id FROM country WHERE code = 'BR'), (SELECT state_id FROM state WHERE name = 'BAHIA'));
