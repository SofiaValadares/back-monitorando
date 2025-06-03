package steps;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefinirHorariosMonitoriaSteps {

    private MonitorEntity monitor;
    private List<StudentEntity> alunos;
    private LocalDateTime horarioMonitoria;
    private String turma;
    private String local;
    private boolean horarioDefinido;
    private boolean compromissosExistentes;
    private String systemMessage;
    private boolean alunosNotificados;
    private List<LocalDateTime> compromissosExistentesLista;

    @Before
    public void setUp() {
// Resetar estados para cada cenário
        monitor = null;
        alunos = new ArrayList<>();
        horarioMonitoria = null;
        turma = null;
        local = null;
        horarioDefinido = false;
        compromissosExistentes = false;
        systemMessage = null;
        alunosNotificados = false;
        compromissosExistentesLista = new ArrayList<>();
    }

// --- Cenário: Horário de monitoria definido com sucesso ---

    @Given("o monitor com status de monitoria {string}")
    public void o_monitor_com_status_monitoria_especifico(String status) {
        monitor = new MonitorEntity(
                1L,
                "Ana",
                "Silva",
                "ana@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                new ArrayList<>()
        );

        alunos.add(new StudentEntity(
                2L,
                "Pedro",
                "Santos",
                "pedro@example.com",
                "senha123",
                UserRole.STUDENT,
                List.of(1L)
        ));

        if (status.equals("ainda não definiu horário para a monitoria")) {
            horarioDefinido = false;
            compromissosExistentes = false;
        } else if (status.equals("já possui compromissos cadastrados em determinados horários")) {
// Simula compromissos existentes
            compromissosExistentes = true;
            compromissosExistentesLista.add(LocalDateTime.of(2024, 6, 10, 14, 0));
            compromissosExistentesLista.add(LocalDateTime.of(2024, 6, 12, 16, 0));
        } else if (status.equals("já possui um horário de monitoria definido")) {
            horarioDefinido = true;
            horarioMonitoria = LocalDateTime.of(2024, 6, 8, 10, 0);
            turma = "CC1";
            local = "Sala 201";
        }
    }

    @When("o monitor seleciona o horário, a turma e o local da monitoria e confirma")
    public void o_monitor_seleciona_o_horario_a_turma_e_o_local_da_monitoria_e_confirma() {
        if (!horarioDefinido && !compromissosExistentes) {
            horarioMonitoria = LocalDateTime.of(2024, 6, 15, 9, 0);
            turma = "CC2";
            local = "Sala 105";
            horarioDefinido = true;
            systemMessage = "Horário da monitoria registrado com sucesso";
        }
    }

    @Then("o sistema registra o horário da monitoria")
    public void o_sistema_registra_o_horario_da_monitoria() {
        assertTrue(horarioDefinido, "O horário da monitoria deveria ter sido registrado");
        assertNotNull(horarioMonitoria, "O horário da monitoria não pode ser nulo");
        assertNotNull(turma, "A turma não pode ser nula");
        assertNotNull(local, "O local não pode ser nulo");
        assertEquals("Horário da monitoria registrado com sucesso", systemMessage);
    }

    @And("o sistema disponibiliza essa informação para os alunos")
    public void o_sistema_disponibiliza_essa_informacao_para_os_alunos() {
        assertTrue(horarioDefinido, "A informação deveria estar disponível para os alunos");
    }

// --- Cenário: Horário de monitoria entra em conflito com outro compromisso ---

    @When("o monitor tenta definir um horário de monitoria que conflita com esses compromissos")
    public void o_monitor_tenta_definir_um_horario_de_monitoria_que_conflita_com_esses_compromissos() {
        LocalDateTime horarioConflitante = LocalDateTime.of(2024, 6, 10, 14, 30);

// Verifica se há conflito com compromissos existentes
        for (LocalDateTime compromisso : compromissosExistentesLista) {
            if (Math.abs(compromisso.toLocalTime().toSecondOfDay() -
                    horarioConflitante.toLocalTime().toSecondOfDay()) < 3600) { // 1 hora de diferença
                systemMessage = "Há conflito de horário";
                horarioDefinido = false;
                break;
            }
        }
    }

    @Then("o sistema informa que há conflito de horário")
    public void o_sistema_informa_que_ha_conflito_de_horario() {
        assertEquals("Há conflito de horário", systemMessage);
        assertFalse(horarioDefinido, "O horário não deveria ter sido definido devido ao conflito");
    }

    @And("o sistema não permite o agendamento da monitoria")
    public void o_sistema_nao_permite_o_agendamento_da_monitoria() {
        assertFalse(horarioDefinido, "A monitoria não deveria ter sido agendada");
    }

// --- Cenário: Alteração de horário de monitoria ---

    @When("o monitor altera o horário da monitoria e confirma a mudança")
    public void o_monitor_altera_o_horario_da_monitoria_e_confirma_a_mudanca() {
        if (horarioDefinido) {
// Altera o horário existente
            horarioMonitoria = LocalDateTime.of(2024, 6, 8, 11, 0); // Nova hora
            turma = "CC1"; // Mantém a turma
            local = "Sala 302"; // Novo local
            systemMessage = "Horário da monitoria atualizado com sucesso";
            alunosNotificados = true;
        }
    }

    @Then("o sistema atualiza o horário da monitoria")
    public void o_sistema_atualiza_o_horario_da_monitoria() {
        assertTrue(horarioDefinido, "O horário deveria ter sido atualizado");
        assertEquals(LocalDateTime.of(2024, 6, 8, 11, 0), horarioMonitoria);
        assertEquals("Sala 302", local);
        assertEquals("Horário da monitoria atualizado com sucesso", systemMessage);
    }

    @And("o sistema notifica os alunos da mudança")
    public void o_sistema_notifica_os_alunos_da_mudanca() {
        assertTrue(alunosNotificados, "Os alunos deveriam ter sido notificados da mudança");
    }
}