Feature: Historia8 - Definir horários de monitorias com alunos

Scenario: Horário de monitoria definido com sucesso
Given o monitor com status de monitoria "ainda não definiu horário para a monitoria"
When o monitor seleciona o horário, a turma e o local da monitoria e confirma
Then o sistema registra o horário da monitoria
And o sistema disponibiliza essa informação para os alunos

Scenario: Horário de monitoria entra em conflito com outro compromisso
Given o monitor com status de monitoria "já possui compromissos cadastrados em determinados horários"
When o monitor tenta definir um horário de monitoria que conflita com esses compromissos
Then o sistema informa que há conflito de horário
And o sistema não permite o agendamento da monitoria

Scenario: Alteração de horário de monitoria
Given o monitor com status de monitoria "já possui um horário de monitoria definido"
When o monitor altera o horário da monitoria e confirma a mudança
Then o sistema atualiza o horário da monitoria
And o sistema notifica os alunos da mudança