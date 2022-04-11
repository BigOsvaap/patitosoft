-- Employee table

CREATE TABLE employee(
    id UUID DEFAULT uuid_generate_v4(),
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
    PRIMARY KEY (id),
    FOREIGN KEY (gender_id) REFERENCES gender(id),
    FOREIGN KEY (country_id) REFERENCES country(id),
    FOREIGN KEY (state_id) REFERENCES state(id)
);

INSERT INTO
    employee (corporate_email, first_name, last_name, personal_email, phone_number, city, street, zip_code, birth_day, gender_id, country_id, state_id)
VALUES
    ('oswaldo.vazquez@patitosoft.com', 'Oswaldo', 'Vazquez Aparicio', 'bigosvaap@gmail.com', '2351012288', 'Misantla', 'Francisco I Mader 119', '93820', '1996-03-22', (SELECT id FROM gender WHERE name = 'Male'), (SELECT id FROM country WHERE code = 'MX'), (SELECT id FROM state WHERE name = 'VERACRUZ')),
    ('arturo.miranda@patitosoft.com', 'Arturo', 'Miranda', 'arturo.miranda@gmail.com', '2282123304', 'Hermosillo', 'Inventada 212', '93909', '1996-08-22', (SELECT id FROM gender WHERE name = 'Female'), (SELECT id FROM country WHERE code = 'MX'), (SELECT id FROM state WHERE name = 'SONORA')),
    ('naomi.ceron@patitosoft.com', 'Naomi', 'Ceron', 'naomi.ceron@gmail.com', '2342123355', 'Hermosillo', 'Inventada 231', '93909', '1998-08-22', (SELECT id FROM gender WHERE name = 'Female'), (SELECT id FROM country WHERE code = 'MX'), (SELECT id FROM state WHERE name = 'SONORA'));
