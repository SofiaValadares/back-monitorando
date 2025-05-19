Feature: Historia1 - Avaliação de atendimento de monitor

  Scenario: Relato enviado com sucesso
    Given um aluno foi atendido por um monitor
    When todas as informações necessárias são fornecidas
    Then o relato é enviado com sucesso
    And o sistema notifica o aluno sobre o envio bem-sucedido

  Scenario: Relato enviado com campos obrigatórios em branco
    Given um aluno foi atendido por um monitor
    When alguma das informações obrigatórias não são fornecidas
    Then o sistema informa que há informações obrigatórias ausentes
    And o sistema informa que o relato não foi registrado