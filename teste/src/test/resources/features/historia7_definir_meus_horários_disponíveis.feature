Scenario: Horários definidos com sucesso
Given o "monitor" "não possui o horário cadastrados para atendimento"  
When o monitor seleciona os horários disponíveis e confirma  
Then o sistema registra os horários disponíveis  
And o sistema os torna visíveis para os alunos  

Scenario: Horários definidos com conflitos
Given o "monitor" "já possui atendimentos agendados em determinados horários"  
When o monitor tenta cadastrar horários que conflitam com esses atendimentos  
Then o sistema informa que há conflito nos horários  
And o sistema não permite o cadastro desses horários  

Scenario: Remoção de horários da agenda
Given o "monitor" "possui horários previamente cadastrados para atendimento"  
When o monitor remove um ou mais desses horários  
Then o sistema atualiza a lista de horários disponíveis  
And o sistema deixa esses horários indisponíveis para os alunos   
