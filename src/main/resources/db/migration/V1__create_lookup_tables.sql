-- For UUID generation
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Gender table

CREATE TABLE gender(
    id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (id)
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
    id UUID DEFAULT uuid_generate_v4(),
    code CHAR(2) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO
    country (code)
VALUES
    ('MX'),
    ('US'),
    ('BR');

-- State table

CREATE TABLE state(
    id UUID DEFAULT uuid_generate_v4(),
    country_id UUID NOT NULL,
    name VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES country(id)
);

WITH mexico AS (
    SELECT * FROM country WHERE code = 'MX'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT mexico.id FROM mexico), 'VERACRUZ'),
       ((SELECT mexico.id FROM mexico), 'SONORA'),
       ((SELECT mexico.id FROM mexico), 'YUCATAN'),
       ((SELECT mexico.id FROM mexico), 'NUEVO LEON');

WITH usa AS (
    SELECT * FROM country WHERE code = 'US'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT usa.id FROM usa), 'TEXAS'),
       ((SELECT usa.id FROM usa), 'ARIZONA'),
       ((SELECT usa.id FROM usa), 'CALIFORNIA'),
       ((SELECT usa.id FROM usa), 'ARKANSAS');

WITH brazil AS (
    SELECT * FROM country WHERE code = 'BR'
)
INSERT INTO state(country_id, name)
VALUES ((SELECT brazil.id FROM brazil), 'ACRE'),
       ((SELECT brazil.id FROM brazil), 'RONDONIA'),
       ((SELECT brazil.id FROM brazil), 'MARANHAO'),
       ((SELECT brazil.id FROM brazil), 'BAHIA');

-- Position table

CREATE TABLE position(
    id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO
    position (name)
VALUES
    ('Software Engineer'),
    ('Talent Acquisition'),
    ('Lawyer'),
    ('Accountant');