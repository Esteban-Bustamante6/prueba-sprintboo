-- Crear tablas de TalentBoard

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) -- ADMIN, RECRUITER, CANDIDATE
);

CREATE TABLE vacancies (
                           id SERIAL PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           description TEXT,
                           category VARCHAR(100),
                           modality VARCHAR(100),
                           salary_range DOUBLE PRECISION,
                           publication_date TIMESTAMP,
                           status VARCHAR(50), -- OPEN, CLOSED, PAUSED
                           user_id BIGINT REFERENCES users(id)
);

CREATE TABLE applications (
                              id SERIAL PRIMARY KEY,
                              candidate_id BIGINT REFERENCES users(id),
                              vacancy_id BIGINT REFERENCES vacancies(id),
                              application_date TIMESTAMP,
                              status VARCHAR(50), -- RECEIVED, INTERVIEW_PHASE, SELECTED, REJECTED
                              observations TEXT
);

CREATE TABLE interviews (
                            id SERIAL PRIMARY KEY,
                            application_id BIGINT UNIQUE REFERENCES applications(id),
                            date TIMESTAMP,
                            type VARCHAR(100),
                            result VARCHAR(255),
                            observations TEXT,
                            interviewer_id BIGINT REFERENCES users(id)
);

INSERT INTO users (username, email, password, role) VALUES ('admin', 'admin@ebl.com', 'encoded_password', 'ADMIN');