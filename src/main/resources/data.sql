-- Inserindo usuários
INSERT INTO users (name, surname, email, password, institution, role, is_active) VALUES
('João', 'Silva', 'joao.silva@email.com', 'senha123', 'CESAR School', 'professor', true),
('Maria', 'Santos', 'maria.santos@email.com', 'senha123', 'CESAR School', 'monitor', true),
('Pedro', 'Oliveira', 'pedro.oliveira@email.com', 'senha123', 'CESAR School', 'aluno', true),
('Ana', 'Costa', 'ana.costa@email.com', 'senha123', 'CESAR School', 'aluno', true),
('Carlos', 'Ferreira', 'carlos.ferreira@email.com', 'senha123', 'CESAR School', 'professor', true);

-- Inserindo professores
INSERT INTO professor (id, department) VALUES
(1, 'Computação'),
(5, 'Design');

-- Inserindo monitores
INSERT INTO monitor (id, time, semester) VALUES
(2, '20h', '2024.1');

-- Inserindo estudantes
INSERT INTO student (id, registration_number, course, semester) VALUES
(3, '20240001', 'Ciência da Computação', 4),
(4, '20240002', 'Design', 3);

-- Inserindo disciplinas
INSERT INTO discipline (name, code, description, semester) VALUES
('Programação 1', 'PROG1', 'Introdução à programação', '2024.1'),
('Estrutura de Dados', 'ED', 'Estruturas de dados avançadas', '2024.1'),
('Design de Interfaces', 'DI', 'Design de interfaces de usuário', '2024.1');

-- Inserindo turmas
INSERT INTO class (discipline_id, professor_id, semester, start_date, end_date, class_code) VALUES
(1, 1, '2024.1', '2024-02-01', '2024-06-30', 'PROG1-2024.1'),
(2, 1, '2024.1', '2024-02-01', '2024-06-30', 'ED-2024.1'),
(3, 5, '2024.1', '2024-02-01', '2024-06-30', 'DI-2024.1');

-- Relacionando professores e disciplinas
INSERT INTO teaches (professor_id, discipline_id, semester) VALUES
(1, 1, '2024.1'),
(1, 2, '2024.1'),
(5, 3, '2024.1');

-- Relacionando monitores e estudantes
INSERT INTO is_monitor (monitor_id, student_id) VALUES
(2, 3),
(2, 4);

-- Registrando matrículas
INSERT INTO attend (student_id, class_id, status) VALUES
(3, 1, 'active'),
(3, 2, 'active'),
(4, 3, 'active');

-- Registrando auxílios de monitoria
INSERT INTO help (monitor_id, discipline_id, semester, assigned_by) VALUES
(2, 1, '2024.1', 1),
(2, 2, '2024.1', 1);

-- Inserindo disponibilidades
INSERT INTO availability (monitor_id, day_of_week, start_time, end_time, location, is_recurring) VALUES
(2, 'Monday', '14:00:00', '16:00:00', 'Sala 101', true),
(2, 'Wednesday', '14:00:00', '16:00:00', 'Sala 101', true);

-- Inserindo agendamentos
INSERT INTO appointment (student_id, monitor_id, discipline_id, appointment_date, start_time, end_time, status) VALUES
(3, 2, 1, '2024-04-22', '14:00:00', '15:00:00', 'scheduled'),
(4, 2, 2, '2024-04-24', '15:00:00', '16:00:00', 'scheduled');

-- Inserindo perguntas
INSERT INTO question (student_id, discipline_id, title, content, is_public, status) VALUES
(3, 1, 'Dúvida sobre loops', 'Como funciona o loop while em Python?', true, 'open'),
(4, 2, 'Dúvida sobre arrays', 'Qual a diferença entre ArrayList e LinkedList?', true, 'open');

-- Inserindo respostas
INSERT INTO answer (question_id, monitor_id, content) VALUES
(1, 2, 'O loop while executa um bloco de código enquanto uma condição for verdadeira...'),
(2, 2, 'ArrayList e LinkedList são implementações diferentes de List...');

-- Inserindo chats
INSERT INTO chat (name, is_group) VALUES
('Monitoria Programação 1', true),
('Monitoria Estrutura de Dados', true);

-- Inserindo participantes nos chats
INSERT INTO chat_participant (chat_id, user_id) VALUES
(1, 1), -- Professor
(1, 2), -- Monitor
(1, 3), -- Aluno
(2, 1), -- Professor
(2, 2), -- Monitor
(2, 4); -- Aluno

-- Inserindo mensagens
INSERT INTO message (chat_id, sender_id, content) VALUES
(1, 2, 'Olá! Estou disponível para tirar dúvidas hoje das 14h às 16h.'),
(1, 3, 'Ótimo! Tenho algumas dúvidas sobre o exercício de loops.');

-- Inserindo tarefas
INSERT INTO task (professor_id, monitor_id, title, description, due_date, status) VALUES
(1, 2, 'Preparar lista de exercícios', 'Criar uma lista de exercícios sobre loops em Python', '2024-04-30', 'pending'),
(1, 2, 'Corrigir trabalhos', 'Corrigir os trabalhos da primeira unidade', '2024-05-15', 'pending');

-- Inserindo notificações
INSERT INTO notification (user_id, aluno_id, type, message) VALUES
(2, 3, 'appointment', 'Novo agendamento de monitoria para segunda-feira'),
(3, 3, 'answer', 'Sua pergunta sobre loops foi respondida'),
(4, 4, 'task', 'Nova tarefa atribuída pelo professor'); 