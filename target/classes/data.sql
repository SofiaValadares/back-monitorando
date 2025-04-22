
-- Inserindo disciplinas
INSERT INTO discipline (id, name, code, description, semester)
SELECT 4, 'Matemática', 'MAT123', 'Disciplina de matemática', '4'
    WHERE NOT EXISTS (SELECT 1 FROM discipline WHERE name = 'Matemática');

INSERT INTO discipline (name, code, description, semester)
SELECT 'Programação 1', 'PROG1', 'Introdução à programação', '2024.1'
    WHERE NOT EXISTS (SELECT 1 FROM discipline WHERE code = 'PROG1');

INSERT INTO discipline (name, code, description, semester)
SELECT 'Estrutura de Dados', 'ED', 'Estruturas de dados avançadas', '2024.1'
    WHERE NOT EXISTS (SELECT 1 FROM discipline WHERE code = 'ED');

INSERT INTO discipline (name, code, description, semester)
SELECT 'Design de Interfaces', 'DI', 'Design de interfaces de usuário', '2024.1'
    WHERE NOT EXISTS (SELECT 1 FROM discipline WHERE code = 'DI');


-- Inserindo usuários
INSERT INTO users (name, surname, email, password, institution, role, is_active)
SELECT 'João', 'Silva', 'joao.silva@email.com', 'senha123', 'CESAR School', 'professor', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'joao.silva@email.com');

INSERT INTO users (name, surname, email, password, institution, role, is_active)
SELECT 'Maria', 'Santos', 'maria.santos@email.com', 'senha123', 'CESAR School', 'monitor', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'maria.santos@email.com');

INSERT INTO users (name, surname, email, password, institution, role, is_active)
SELECT 'Pedro', 'Oliveira', 'pedro.oliveira@email.com', 'senha123', 'CESAR School', 'aluno', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'pedro.oliveira@email.com');

INSERT INTO users (name, surname, email, password, institution, role, is_active)
SELECT 'Ana', 'Costa', 'ana.costa@email.com', 'senha123', 'CESAR School', 'aluno', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'ana.costa@email.com');

INSERT INTO users (name, surname, email, password, institution, role, is_active)
SELECT 'Carlos', 'Ferreira', 'carlos.ferreira@email.com', 'senha123', 'CESAR School', 'professor', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'carlos.ferreira@email.com');


-- Inserindo professores
INSERT INTO professor (id, department)
SELECT 1, 'Computação'
    WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 1);

INSERT INTO professor (id, department)
SELECT 5, 'Design'
    WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 5);


-- Inserindo monitores
INSERT INTO monitor (id, time, semester, discipline_id)
SELECT 2, '20h', '2024.1', 4
    WHERE NOT EXISTS (SELECT 1 FROM monitor WHERE id = 2);


-- Inserindo estudantes
INSERT INTO student (id, registration_number, course, semester, discipline_id)
SELECT 3, '20240001', 'Ciência da Computação', 4, 4
    WHERE NOT EXISTS (SELECT 1 FROM student WHERE registration_number = '20240001');

INSERT INTO student (id, registration_number, course, semester, discipline_id)
SELECT 4, '20240002', 'Design', 3, 4
    WHERE NOT EXISTS (SELECT 1 FROM student WHERE registration_number = '20240002');
