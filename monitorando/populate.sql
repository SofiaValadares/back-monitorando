-- Popula tabela users (corrigido com surname)
INSERT INTO users (id, email, name, surname, password, role)
VALUES
    (1, 'xavier@monitorando.com', 'Professor', 'Xavier', 'senha123', 'PROFESSOR'),
    (2, 'jean@monitorando.com', 'Jean', 'Grey', 'senha123', 'STUDENT'),
    (3, 'scott@monitorando.com', 'Scott', 'Summers', 'senha123', 'STUDENT'),
    (4, 'ororo@monitorando.com', 'Ororo', 'Munroe', 'senha123', 'MONITOR');

-- Popula tabela student
INSERT INTO students (id)
VALUES
    (2),
    (3);

-- Popula tabela discipline
INSERT INTO disciplines (id, name, code)
VALUES
    (1, 'Álgebra Linear', 'adibnei'),
    (2, 'Estrutura de Dados', 'dfiwbn');

-- Popula tabela monitor
INSERT INTO monitors (id)
VALUES
    (1);

insert into monitor_discipline (monitor_id, discipline_id)
values
    (1, 1);

