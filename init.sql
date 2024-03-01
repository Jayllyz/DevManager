CREATE TABLE Skill
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE Project
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    deadline DATE NOT NULL,
    priority VARCHAR(120) NOT NULL,
    status VARCHAR(120) NOT NULL
);

CREATE TABLE skills_for_project
(
    dev_number INT NOT NULL,
    skill_id INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (skill_id, project_id),
    FOREIGN KEY (skill_id) REFERENCES Skill(id),
    FOREIGN KEY (project_id) REFERENCES Project(id)
);

CREATE TABLE Developer
(
    email VARCHAR(120) PRIMARY KEY,
    last_name VARCHAR(120) NOT NULL,
    first_name VARCHAR(120) NOT NULL
);

CREATE TABLE developer_skills
(
    years_experience INT NOT NULL,
    dev_email VARCHAR(120) NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (dev_email, skill_id),
    FOREIGN KEY (dev_email) REFERENCES Developer(email),
    FOREIGN KEY (skill_id) REFERENCES Skill(id)
);

CREATE TABLE team
(
    dev_id VARCHAR(120) NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (dev_id, project_id),
    FOREIGN KEY (dev_id) REFERENCES Developer(email),
    FOREIGN KEY (project_id) REFERENCES Project(id)
);

INSERT INTO Developer (email, last_name, first_name)
VALUES
    ('john@example.com', 'Smith', 'John'),
    ('alice@example.com', 'Johnson', 'Alice');

INSERT INTO Skill (name)
VALUES
    ('Java'),
    ('Python'),
    ('SQL');

INSERT INTO developer_skills (years_experience, dev_email, skill_id)
VALUES
    (5, 'john@example.com', 1), -- Java skill for developer1
    (3, 'john@example.com', 2), -- Python skill for developer1
    (2, 'alice@example.com', 2), -- Python skill for developer2
    (4, 'alice@example.com', 3); -- SQL skill for developer2


INSERT INTO Project (name, description, start_date, deadline, priority, status)
VALUES
    ('Projet Annuel', 'Web application development', '2024-03-18', '2024-04-15', 'normal', 'done'),
    ('TESTINGS', 'Data analytics platform', '2024-03-15', '2024-05-30', 'best-effort', 'done');


INSERT INTO team (dev_id, project_id)
VALUES
    ('john@example.com', 1), -- developer1 assigned to Project A
    ('alice@example.com', 1), -- developer2 also assigned to Project A
    ('alice@example.com', 2); -- developer2 assigned to Project B

INSERT INTO skills_for_project (dev_number, skill_id, project_id)
VALUES
    (1,2,1), -- developer1 assigned to Project A
    (2,3,1), -- developer2 also assigned to Project A
    (2,2,2); -- developer2 also assigned to Project A
