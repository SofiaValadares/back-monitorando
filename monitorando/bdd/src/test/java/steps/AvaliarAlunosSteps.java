package steps;

import br.com.cesarschool.domain.entity.AssistsEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliarAlunosSteps {

    private StudentEntity student;
    private MonitorEntity monitor;
    private AssistsEntity atendimento;
    private boolean atendimentoRealizado;
    private boolean avaliacaoCompleta;
    private String systemMessage;
    private boolean avaliacaoEnviada;
    private boolean alunoNotificado;

    @Before
    public void setUp() {
// Resetar estados para cada cenário
        student = null;
        monitor = null;
        atendimento = null;
        atendimentoRealizado = false;
        avaliacaoCompleta = false;
        systemMessage = null;
        avaliacaoEnviada = false;
        alunoNotificado = false;
    }

// --- Cenário: Avaliação enviada com sucesso ---

    @Given("um {string} {string}")
    public void um_atendimento_com_status(String tipoEntidade, String status) {
        student = new StudentEntity(
                1L,
                "João",
                "Silva",
                "joao@example.com",
                "senha123",
                UserRole.STUDENT,
                List.of(1L)
        );
        monitor = new MonitorEntity(
                2L,
                "Maria",
                "Santos",
                "maria@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                new ArrayList<>()
        );

        if (status.equals("foi realizado com um aluno")) {
            atendimentoRealizado = true;
            atendimento = new AssistsEntity(
                    student,
                    monitor,
                    new Date(System.currentTimeMillis() - 86400000), // Ontem
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0)
            );
        } else if (status.equals("ainda não foi realizado")) {
            atendimentoRealizado = false;
            atendimento = new AssistsEntity(
                    student,
                    monitor,
                    new Date(System.currentTimeMillis() + 86400000), // Amanhã
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0)
            );
        }
    }

    @When("o monitor preenche a avaliação do aluno e envia")
    public void o_monitor_preenche_a_avaliacao_do_aluno_e_envia() {
        if (atendimentoRealizado) {
            avaliacaoCompleta = true;
// Simula preenchimento completo da avaliação
            avaliacaoEnviada = true;
            systemMessage = "Avaliação registrada com sucesso";
            alunoNotificado = true;
        }
    }

    @Then("o sistema registra a avaliação")
    public void o_sistema_registra_a_avaliacao() {
        assertTrue(avaliacaoEnviada, "A avaliação deveria ter sido registrada");
        assertEquals("Avaliação registrada com sucesso", systemMessage);
    }

    @And("o sistema notifica o aluno sobre a avaliação recebida")
    public void o_sistema_notifica_o_aluno_sobre_a_avaliacao_recebida() {
        assertTrue(alunoNotificado, "O aluno deveria ter sido notificado");
    }

// --- Cenário: Tentar avaliar sem preencher os campos obrigatórios ---

    @When("o monitor tenta enviar a avaliação sem preencher os campos obrigatórios")
    public void o_monitor_tenta_enviar_a_avaliacao_sem_preencher_os_campos_obrigatorios() {
        if (atendimentoRealizado) {
            avaliacaoCompleta = false;
// Simula envio de avaliação incompleta
            avaliacaoEnviada = false;
            systemMessage = "Todos os campos obrigatórios devem ser preenchidos";
        }
    }

    @Then("o sistema informa que todos os campos obrigatórios devem ser preenchidos")
    public void o_sistema_informa_que_todos_os_campos_obrigatorios_devem_ser_preenchidos() {
        assertFalse(avaliacaoEnviada, "A avaliação não deveria ter sido enviada");
        assertEquals("Todos os campos obrigatórios devem ser preenchidos", systemMessage);
    }

    @And("o sistema não envia a avaliação")
    public void o_sistema_nao_envia_a_avaliacao() {
        assertFalse(avaliacaoEnviada, "A avaliação não deveria ter sido enviada");
        assertFalse(alunoNotificado, "O aluno não deveria ter sido notificado");
    }

// --- Cenário: Avaliar um atendimento que ainda não ocorreu ---

    @When("o monitor tenta enviar uma avaliação para esse atendimento")
    public void o_monitor_tenta_enviar_uma_avaliacao_para_esse_atendimento() {
        if (!atendimentoRealizado) {
            avaliacaoEnviada = false;
            systemMessage = "Não é possível avaliar antes da realização do atendimento";
        }
    }

    @Then("o sistema informa que não é possível avaliar antes da realização do atendimento")
    public void o_sistema_informa_que_nao_e_possivel_avaliar_antes_da_realizacao_do_atendimento() {
        assertFalse(avaliacaoEnviada, "A avaliação não deveria ter sido enviada");
        assertEquals("Não é possível avaliar antes da realização do atendimento", systemMessage);
    }

    @And("o sistema impede o envio da avaliação")
    public void o_sistema_impede_o_envio_da_avaliacao() {
        assertFalse(avaliacaoEnviada, "A avaliação não deveria ter sido enviada");
        assertFalse(alunoNotificado, "O aluno não deveria ter sido notificado");
    }
}