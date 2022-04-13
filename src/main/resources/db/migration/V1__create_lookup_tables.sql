-- For UUID generation
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Gender table

CREATE TABLE gender(
    gender_id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (gender_id)
);

INSERT INTO
    gender (name)
VALUES
    ('Female'),
    ('Male'),
    ('Non binary'),
    ('I prefer not to say');

-- Country table

CREATE TABLE country(
    country_id UUID DEFAULT uuid_generate_v4(),
    code CHAR(2) UNIQUE NOT NULL,
    PRIMARY KEY (country_id)
);

INSERT INTO
    country (code)
VALUES
    ('MX'),
    ('US'),
    ('BR');

-- State table

CREATE TABLE state(
    state_id UUID DEFAULT uuid_generate_v4(),
    country_id UUID NOT NULL,
    name VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY (state_id),
    FOREIGN KEY (country_id) REFERENCES country(country_id)
);

WITH mexico AS (
    SELECT * FROM country WHERE code = 'MX'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT mexico.country_id FROM mexico), 'VERACRUZ'),
       ((SELECT mexico.country_id FROM mexico), 'SONORA'),
       ((SELECT mexico.country_id FROM mexico), 'YUCATAN'),
       ((SELECT mexico.country_id FROM mexico), 'NUEVO LEON');

WITH usa AS (
    SELECT * FROM country WHERE code = 'US'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT usa.country_id FROM usa), 'TEXAS'),
       ((SELECT usa.country_id FROM usa), 'ARIZONA'),
       ((SELECT usa.country_id FROM usa), 'CALIFORNIA'),
       ((SELECT usa.country_id FROM usa), 'ARKANSAS');

WITH brazil AS (
    SELECT * FROM country WHERE code = 'BR'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT brazil.country_id FROM brazil), 'ACRE'),
       ((SELECT brazil.country_id FROM brazil), 'RONDONIA'),
       ((SELECT brazil.country_id FROM brazil), 'MARANHAO'),
       ((SELECT brazil.country_id FROM brazil), 'BAHIA');

-- Position table

CREATE TABLE position(
    position_id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY (position_id)
);

INSERT INTO
    position (name)
VALUES
    ('Software Engineer'),
    ('Talent Acquisition'),
    ('Lawyer'),
    ('Accountant');