Feature: Historia4 - Marcar atendimento com algum monitor

    Scenario: Atendimento agendado com monitor disponivel
        Given um monitor possui horarios disponiveis
        When um aluno solicita o agendamento de um atendimento
        Then o sistema agenda o atendimento
        And notifica o aluno com a confirmacao do agendamento

    Scenario: Tentativa de agendamento com monitor indisponivel
        Given um monitor nao possui horarios disponiveis
        When um aluno solicita o agendamento de um atendimento
        Then o sistema informa que o monitor esta indisponivel para agendamentos