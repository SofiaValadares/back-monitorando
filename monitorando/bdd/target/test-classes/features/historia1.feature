Feature: Historia1 - Relatar uma experiencia com um monitor

    Scenario: Relato enviado com sucesso
        Given um aluno foi atendido por um monitor
        When todas as informacoes necessarias sao fornecidas
        Then o relato e enviado com sucesso
        And o sistema notifica o aluno sobre o envio bem sucedido

    Scenario: Relato enviado com campos obrigatorios em branco
        Given um aluno ja foi atendido por um monitor
        When alguma das informacoes obrigatorias nao sao fornecidas
        Then o sistema informa que ha informacoes obrigatorias ausentes
        And o sistema informa que o relato nao foi registrado