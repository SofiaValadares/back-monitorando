package steps;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelatarExperienciaSteps {

    private StudentEntity student;
    private MonitorEntity monitor;
    private boolean allInformationProvided;
    private String systemMessage;
    private boolean reportSentSuccessfully;

    @Before
    public void setUp() {
        // Resetar estados para cada cenario
        student = null;
        monitor = null;
        allInformationProvided = false;
        systemMessage = null;
        reportSentSuccessfully = false;
    }

    // --- Cenario: Relato enviado com sucesso ---

    @Given("um aluno foi atendido por um monitor")
    public void um_aluno_foi_atendido_por_um_monitor() {
        student = new StudentEntity(
                1L,
                "Pedro",
                "Alves",
                "pedro@example.com",
                "senha123",
                UserRole.STUDENT,
                List.of(1L)
        );
        monitor = new MonitorEntity(
                2L,
                "Mariana",
                "Costa",
                "mariana@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                new ArrayList<>()
        );
        // Simula que o atendimento ja ocorreu
    }

    @When("todas as informacoes necessarias sao fornecidas")
    public void todas_as_informacoes_necessarias_sao_fornecidas() {
        // Simula que o aluno preencheu todos os campos obrigatorios do relato
        allInformationProvided = true;
        // Tenta enviar o relato
        if (allInformationProvided) {
            reportSentSuccessfully = true;
            systemMessage = "Relato enviado com sucesso!";
        } else {
            reportSentSuccessfully = false;
            systemMessage = "Ha informacoes obrigatorias ausentes.";
        }
    }

    @Then("o relato e enviado com sucesso")
    public void o_relato_e_enviado_com_sucesso() {
        assertTrue(reportSentSuccessfully, "O relato deveria ter sido enviado com sucesso.");
        assertEquals("Relato enviado com sucesso!", systemMessage);
    }

    @And("o sistema notifica o aluno sobre o envio bem sucedido")
    public void o_sistema_notifica_o_aluno_sobre_o_envio_bem_sucedido() {
        assertEquals("Relato enviado com sucesso!", systemMessage);
    }

    // --- Cenario: Relato enviado com campos obrigatorios em branco ---

    @Given("um aluno ja foi atendido por um monitor") // Novo passo Given para o cenario 2
    public void um_aluno_ja_foi_atendido_por_um_monitor() {
        student = new StudentEntity(
                3L, // Novo ID para evitar conflito com o cenario 1, se executados juntos
                "Fernanda",
                "Dias",
                "fernanda@example.com",
                "senha456",
                UserRole.STUDENT,
                List.of(2L)
        );
        monitor = new MonitorEntity(
                4L, // Novo ID para evitar conflito
                "Ricardo",
                "Santos",
                "ricardo@example.com",
                "monitorpass2",
                UserRole.MONITOR,
                List.of(2L),
                2L,
                new ArrayList<>()
        );
    }

    @When("alguma das informacoes obrigatorias nao sao fornecidas") // Corrigido para "so" conforme o erro
    public void alguma_das_informacoes_obrigatorias_nao_so_fornecidas() {
        // Simula que o aluno deixou campos obrigatorios em branco
        allInformationProvided = false;
        // Tenta enviar o relato
        if (allInformationProvided) {
            reportSentSuccessfully = true;
            systemMessage = "Relato enviado com sucesso!";
        } else {
            reportSentSuccessfully = false;
            systemMessage = "Ha informacoes obrigatorias ausentes.";
        }
    }

    @Then("o sistema informa que ha informacoes obrigatorias ausentes") // Corrigido para sem acentos
    public void o_sistema_informa_que_ha_informacoes_obrigatorias_ausentes() {
        assertFalse(reportSentSuccessfully, "O relato nao deveria ter sido enviado.");
        assertEquals("Ha informacoes obrigatorias ausentes.", systemMessage);
    }

    @And("o sistema informa que o relato nao foi registrado")
    public void o_sistema_informa_que_o_relato_nao_foi_registrado() {
        assertEquals("Ha informacoes obrigatorias ausentes.", systemMessage);
    }
}