Scenario: Avaliação enviada com sucesso
Given um "atendimento" "foi realizado com um aluno"  
When o monitor preenche a avaliação do aluno e envia  
Then o sistema registra a avaliação  
And o sistema notifica o aluno sobre a avaliação recebida  

Scenario: Tentar avaliar sem preencher os campos obrigatórios
Given um "atendimento" "foi realizado com um aluno"  
When o monitor tenta enviar a avaliação sem preencher os campos obrigatórios  
Then o sistema informa que todos os campos obrigatórios devem ser preenchidos  
And o sistema não envia a avaliação   

Scenario: Avaliar um atendimento que ainda não ocorreu
Given um "atendimento" "ainda não foi realizado"  
When o monitor tenta enviar uma avaliação para esse atendimento  
Then o sistema informa que não é possível avaliar antes da realização do atendimento  
And o sistema impede o envio da avaliação 
