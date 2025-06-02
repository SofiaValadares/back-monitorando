Feature: Historia3 - Enviar uma dúvida a um monitor específico

  Scenario: Dúvida registrada com sucesso
    Given um aluno iniciou o envio de uma dúvida
    And selecionou um monitor
    When a dúvida for enviada
    Then o sistema registra a dúvida
    And encaminha a dúvida ao monitor selecionado
    And notifica o aluno sobre o envio bem-sucedido

  Scenario: Dúvida sem monitor selecionado
    Given um aluno iniciou o envio de uma dúvida
    When o monitor não é selecionado
    Then o sistema informa que é necessário selecionar um monitor
    And não registra a dúvida

  Scenario: Dúvida com mensagem vazia
    Given um aluno iniciou o envio de uma dúvida
    When a mensagem não existe
    Then o sistema informa que a mensagem é obrigatória
    And não registra a dúvida