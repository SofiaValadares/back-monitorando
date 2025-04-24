
Feature: Historia4 - Marcar atendimento com algum monitor

  Scenario: Atendimento agendado com monitor disponível
    Given um monitor possui horários disponíveis
    When um aluno solicita o agendamento de um atendimento
    Then o sistema agenda o atendimento com status pendente para aprovacao

  Scenario: Tentativa de agendamento com monitor indisponível
    Given um monitor não possui horários disponíveis
    When um aluno solicita o agendamento de um atendimento
    Then o sistema informa que o monitor está indisponível para agendamentos