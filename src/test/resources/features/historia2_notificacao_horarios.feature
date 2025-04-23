Feature: Historia2 - Receber notificação de horários dos monitores

  Scenario: Horários do monitor atualizados
    Given um monitor atualizou ou cadastrou seus horários disponíveis
    When o sistema identifica a atualização dos horários
    Then o sistema envia uma notificação aos alunos cadastrados na disciplina
    And o sistema informa os novos horários disponíveis do monitor

  Scenario: Horário inválido informado pelo monitor
    Given um monitor informou um horário para disponibilidade
    When o horário informado não é válido
    Then o sistema rejeita a atualização dos horários
    And o sistema informa ao monitor que o horário informado é inválido
