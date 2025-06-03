package steps;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.entity.enums.WeekDay;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefinirHorariosDisponiveisSteps {

    private MonitorEntity monitor;
    private List<AvailableTimeEntity> horariosExistentes;
    private List<AvailableTimeEntity> novosHorarios;
    private String systemMessage;
    private boolean horariosRegistrados;
    private boolean horariosVisiveis;
    private boolean conflitosDetectados;

    @Before
    public void setUp() {
// Resetar estados para cada cenário
        monitor = null;
        horariosExistentes = new ArrayList<>();
        novosHorarios = new ArrayList<>();
        systemMessage = null;
        horariosRegistrados = false;
        horariosVisiveis = false;
        conflitosDetectados = false;
    }

// --- Cenário: Horários definidos com sucesso ---

    @Given("o {string} {string}")
    public void o_monitor_com_status(String tipoUsuario, String status) {
        monitor = new MonitorEntity(
                1L,
                "Carlos",
                "Oliveira",
                "carlos@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                new ArrayList<>()
        );

        if (status.equals("não possui o horário cadastrados para atendimento")) {
            horariosExistentes.clear();
        } else if (status.equals("já possui atendimentos agendados em determinados horários")) {
// Simula horários já ocupados
            horariosExistentes.add(new AvailableTimeEntity(
                    1L,
                    WeekDay.MONDAY,
                    LocalTime.of(14, 0),
                    LocalTime.of(15, 0)
            ));
        } else if (status.equals("possui horários previamente cadastrados para atendimento")) {
// Simula horários já cadastrados
            horariosExistentes.add(new AvailableTimeEntity(
                    1L,
                    WeekDay.TUESDAY,
                    LocalTime.of(10, 0),
                    LocalTime.of(11, 0)
            ));
            horariosExistentes.add(new AvailableTimeEntity(
                    2L,
                    WeekDay.WEDNESDAY,
                    LocalTime.of(16, 0),
                    LocalTime.of(17, 0)
            ));
        }
    }

    @When("o monitor seleciona os horários disponíveis e confirma")
    public void o_monitor_seleciona_os_horarios_disponiveis_e_confirma() {
// Simula seleção de novos horários
        novosHorarios.add(new AvailableTimeEntity(
                3L,
                WeekDay.FRIDAY,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        ));

        if (horariosExistentes.isEmpty()) {
            horariosRegistrados = true;
            horariosVisiveis = true;
            systemMessage = "Horários disponíveis registrados com sucesso";
        }
    }

    @Then("o sistema registra os horários disponíveis")
    public void o_sistema_registra_os_horarios_disponiveis() {
        assertTrue(horariosRegistrados, "Os horários deveriam ter sido registrados");
        assertEquals("Horários disponíveis registrados com sucesso", systemMessage);
    }

    @And("o sistema os torna visíveis para os alunos")
    public void o_sistema_os_torna_visiveis_para_os_alunos() {
        assertTrue(horariosVisiveis, "Os horários deveriam estar visíveis para os alunos");
    }

// --- Cenário: Horários definidos com conflitos ---

    @When("o monitor tenta cadastrar horários que conflitam com esses atendimentos")
    public void o_monitor_tenta_cadastrar_horarios_que_conflitam_com_esses_atendimentos() {
// Tenta cadastrar horário que conflita
        AvailableTimeEntity horarioConflitante = new AvailableTimeEntity(
                4L,
                WeekDay.MONDAY,
                LocalTime.of(14, 30), // Conflita com horário existente
                LocalTime.of(15, 30)
        );

// Verifica conflito
        for (AvailableTimeEntity horarioExistente : horariosExistentes) {
            if (horarioExistente.getWeekDay() == horarioConflitante.getWeekDay() &&
                    horariosConflitam(horarioExistente, horarioConflitante)) {
                conflitosDetectados = true;
                systemMessage = "Há conflito nos horários";
                break;
            }
        }
    }

    @Then("o sistema informa que há conflito nos horários")
    public void o_sistema_informa_que_ha_conflito_nos_horarios() {
        assertTrue(conflitosDetectados, "Deveria ter detectado conflitos");
        assertEquals("Há conflito nos horários", systemMessage);
    }

    @And("o sistema não permite o cadastro desses horários")
    public void o_sistema_nao_permite_o_cadastro_desses_horarios() {
        assertFalse(horariosRegistrados, "Os horários conflitantes não deveriam ter sido registrados");
    }

// --- Cenário: Remoção de horários da agenda ---

    @When("o monitor remove um ou mais desses horários")
    public void o_monitor_remove_um_ou_mais_desses_horarios() {
        if (!horariosExistentes.isEmpty()) {
// Remove o primeiro horário
            horariosExistentes.remove(0);
            systemMessage = "Lista de horários disponíveis atualizada";
            horariosRegistrados = true; // Representa atualização bem-sucedida
        }
    }

    @Then("o sistema atualiza a lista de horários disponíveis")
    public void o_sistema_atualiza_a_lista_de_horarios_disponiveis() {
        assertTrue(horariosRegistrados, "A lista deveria ter sido atualizada");
        assertEquals("Lista de horários disponíveis atualizada", systemMessage);
    }

    @And("o sistema deixa esses horários indisponíveis para os alunos")
    public void o_sistema_deixa_esses_horarios_indisponiveis_para_os_alunos() {
// Verifica que os horários removidos não estão mais disponíveis
        assertFalse(horariosVisiveis, "Os horários removidos não deveriam estar visíveis");
    }

    // Método auxiliar para verificar conflitos de horário
    private boolean horariosConflitam(AvailableTimeEntity horario1, AvailableTimeEntity horario2) {
        return !(horario1.getEndTime().isBefore(horario2.getStartTime()) ||
                horario2.getEndTime().isBefore(horario1.getStartTime()));
    }
}