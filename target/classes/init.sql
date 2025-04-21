-- Tabela de usuários - Armazena informações básicas de todos os usuários do sistema
CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name varchar(31) not null,
    surname varchar (101) not null,
    email varchar(31) UNIQUE not null,
    password varchar(15) not null,
    institution varchar(21) not null,
    role VARCHAR(20) CHECK (role IN ('aluno', 'monitor', 'professor')) NOT NULL,
    is_active BOOLEAN DEFAULT true
);

-- Tabela de estudantes - Armazena informações específicas dos usuários que são alunos
CREATE TABLE IF NOT EXISTS student(
    id INT PRIMARY KEY,
    registration_number VARCHAR(20) UNIQUE,
    course VARCHAR(100),
    semester INT,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela de professores - Armazena informações específicas dos usuários que são professores
CREATE TABLE IF NOT EXISTS professor(
    id INT PRIMARY KEY,
    department VARCHAR(100),
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela de monitores - Armazena informações específicas dos usuários que são monitores
CREATE TABLE IF NOT EXISTS monitor(
    id INT PRIMARY KEY,
    time VARCHAR(50),
    semester VARCHAR(20),
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela de disciplinas - Armazena informações sobre as disciplinas oferecidas
CREATE TABLE IF NOT EXISTS discipline(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
    description TEXT,
    semester VARCHAR(20)
);

-- Tabela de turmas - Representa uma instância específica de uma disciplina em um semestre
CREATE TABLE IF NOT EXISTS class(
    id SERIAL PRIMARY KEY,
    discipline_id INT NOT NULL,
    professor_id INT NOT NULL,
    semester VARCHAR(20) NOT NULL,
    start_date DATE,
    end_date DATE,
    class_code VARCHAR(20) UNIQUE,
    FOREIGN KEY (discipline_id) REFERENCES discipline(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela de relação entre professores e disciplinas - Indica quais disciplinas cada professor leciona
CREATE TABLE IF NOT EXISTS teaches(
    professor_id INT,
    discipline_id INT,
    semester VARCHAR(20) NOT NULL,
    PRIMARY KEY (professor_id, discipline_id, semester),
    FOREIGN KEY (professor_id) REFERENCES professor(id),
    FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);

-- Tabela de relação entre monitores e estudantes - Indica quais alunos são monitorados por cada monitor
CREATE TABLE IF NOT EXISTS is_monitor(
    monitor_id INT,
    student_id INT,
    PRIMARY KEY (monitor_id, student_id),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

-- Tabela de matrículas - Registra quais alunos estão matriculados em quais turmas
CREATE TABLE IF NOT EXISTS attend(
    student_id INT,
    class_id INT,
    status VARCHAR(20) CHECK (status IN ('active', 'dropped', 'completed')) DEFAULT 'active',
    PRIMARY KEY (student_id, class_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (class_id) REFERENCES class(id)
);

-- Tabela de auxílio - Registra quais disciplinas cada monitor auxilia
CREATE TABLE IF NOT EXISTS help(
    monitor_id INT,
    discipline_id INT,
    semester VARCHAR(20) NOT NULL,
    assigned_by INT NOT NULL,
    PRIMARY KEY (monitor_id, discipline_id, semester),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id),
    FOREIGN KEY (discipline_id) REFERENCES discipline(id),
    FOREIGN KEY (assigned_by) REFERENCES professor(id)
);

-- Tabela de disponibilidade - Registra os horários disponíveis de cada monitor
CREATE TABLE IF NOT EXISTS availability(
    id SERIAL PRIMARY KEY,
    monitor_id INT NOT NULL,
    day_of_week VARCHAR(20) CHECK (day_of_week IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    location VARCHAR(100),
    is_recurring BOOLEAN DEFAULT true,
    FOREIGN KEY (monitor_id) REFERENCES monitor(id)
);

-- Tabela de agendamentos - Registra os atendimentos agendados entre alunos e monitores
CREATE TABLE IF NOT EXISTS appointment(
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    monitor_id INT NOT NULL,
    discipline_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(20) CHECK (status IN ('scheduled', 'completed', 'cancelled', 'no_show')) DEFAULT 'scheduled',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id),
    FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);

-- Tabela de relatórios de atendimento - Armazena os relatórios feitos pelos monitores após os atendimentos
CREATE TABLE IF NOT EXISTS appointment_report(
    id SERIAL PRIMARY KEY,
    appointment_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);

-- Tabela de perguntas - Armazena as dúvidas enviadas pelos alunos
CREATE TABLE IF NOT EXISTS question(
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    discipline_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    is_public BOOLEAN DEFAULT true,
    status VARCHAR(20) CHECK (status IN ('open', 'answered', 'closed')) DEFAULT 'open',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);

-- Tabela de avaliações - Permite avaliações bidirecionais entre alunos e monitores
CREATE TABLE IF NOT EXISTS evaluation(
    id SERIAL PRIMARY KEY,
    evaluator_id INT NOT NULL,
    evaluated_id INT NOT NULL,
    appointment_id INT,
    question_id INT,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (evaluator_id) REFERENCES user(id),
    FOREIGN KEY (evaluated_id) REFERENCES user(id),
    FOREIGN KEY (appointment_id) REFERENCES appointment(id),
    FOREIGN KEY (question_id) REFERENCES question(id),
    CHECK (appointment_id IS NOT NULL OR question_id IS NOT NULL)
    );

-- Tabela de respostas - Armazena as respostas dos monitores às perguntas dos alunos
CREATE TABLE IF NOT EXISTS answer(
    id SERIAL PRIMARY KEY,
    question_id INT NOT NULL,
    monitor_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id)
);

-- Tabela de chats - Armazena informações sobre conversas entre usuários
CREATE TABLE IF NOT EXISTS chat(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    is_group BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de participantes de chat - Registra quais usuários participam de quais chats
CREATE TABLE IF NOT EXISTS chat_participant(
    chat_id INT,
    user_id INT,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (chat_id, user_id),
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tabela de mensagens - Armazena as mensagens enviadas nos chats
CREATE TABLE IF NOT EXISTS message(
    id SERIAL PRIMARY KEY,
    chat_id INT NOT NULL,
    sender_id INT NOT NULL,
    content TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (sender_id) REFERENCES users(id)
);

-- Tabela de tarefas - Armazena tarefas atribuídas pelos professores aos monitores
CREATE TABLE IF NOT EXISTS task(
    id SERIAL PRIMARY KEY,
    professor_id INT NOT NULL,
    monitor_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(20) CHECK (status IN ('pending', 'in_progress', 'completed', 'cancelled')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (professor_id) REFERENCES professor(id),
    FOREIGN KEY (monitor_id) REFERENCES monitor(id)
);

-- Tabela de notificações
CREATE TABLE IF NOT EXISTS notification(
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    aluno_id INT,
    type VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    read BOOLEAN DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (aluno_id) REFERENCES student(id)
);