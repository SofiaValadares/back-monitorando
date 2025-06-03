Scenario: Resposta enviada com sucesso
Given uma "dúvida" "foi enviada por um aluno"  
When o monitor escreve a resposta e envia  
Then o sistema registra a resposta  
And o sistema notifica o aluno sobre a resposta recebida

Scenario: Tentar responder dúvida sem escrever mensagem
Given uma "dúvida" "foi enviada por um aluno"  
When o monitor tenta enviar a resposta sem preencher o campo de mensagem  
Then o sistema informa que o campo de resposta é obrigatório  
And o sistema não envia a resposta 

Scenario: Dúvida já respondida
Given uma "dúvida" "já foi respondida anteriormente"  
When o monitor tenta enviar uma nova resposta para a mesma dúvida  
Then o sistema informa que a dúvida já foi respondida  
And o sistema impede o envio de outra resposta  
