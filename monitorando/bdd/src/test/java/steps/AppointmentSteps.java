package steps;

import br.com.cesarschool.domain.entity.AssistsEntity;
import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.entity.enums.WeekDay;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentSteps {

    private StudentEntity student;
    private MonitorEntity monitor;
    private List<AvailableTimeEntity> monitorAvailableTimes;
    private AssistsEntity scheduledAssistance;
    private String systemMessage;
    private Date requestedDate;
    private LocalTime requestedStartTime;
    private LocalTime requestedEndTime;

    @Before
    public void setUp() {
        // Resetar estados para cada cenario
        student = null;
        monitor = null;
        monitorAvailableTimes = new ArrayList<>();
        scheduledAssistance = null;
        systemMessage = null;
        requestedDate = null;
        requestedStartTime = null;
        requestedEndTime = null;

        // Configuracoes padrao que podem ser usadas/sobrescritas pelos cenarios
        student = new StudentEntity(
                1L,
                "Alice",
                "Souza",
                "alice@example.com",
                "password",
                UserRole.STUDENT,
                List.of(10L)
        );
        LocalDate futureLocalDate = LocalDate.now(ZoneId.of("America/Sao_Paulo")).plusDays(3);
        if (futureLocalDate.getDayOfWeek() == java.time.DayOfWeek.SATURDAY) {
            futureLocalDate = futureLocalDate.plusDays(2);
        } else if (futureLocalDate.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
            futureLocalDate = futureLocalDate.plusDays(1);
        }
        requestedDate = Date.from(futureLocalDate.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant());
        requestedStartTime = LocalTime.of(9, 30);
        requestedEndTime = LocalTime.of(10, 30);
    }

    // --- Cenário: Atendimento agendado com monitor disponivel ---

    @Given("um monitor possui horarios disponiveis") // Removido acento e sufixo
    public void um_monitor_possui_horarios_disponiveis() {
        monitor = new MonitorEntity(
                2L,
                "Carlos",
                "Lima",
                "carlos@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(10L),
                10L,
                new ArrayList<>()
        );
        LocalDate futureLocalDate = LocalDate.ofInstant(requestedDate.toInstant(), ZoneId.of("America/Sao_Paulo"));
        monitorAvailableTimes.add(new AvailableTimeEntity(
                101L,
                WeekDay.valueOf(futureLocalDate.getDayOfWeek().name()),
                LocalTime.of(9, 0),
                LocalTime.of(11, 0)
        ));
    }

    @When("um aluno solicita o agendamento de um atendimento") // Removido sufixo
    public void um_aluno_solicita_o_agendamento_de_um_atendimento() {
        if (student == null || monitor == null) {
            systemMessage = "Erro: Aluno ou monitor nao inicializado."; // Removido acento
            return;
        }

        boolean isMonitorAvailable = monitorAvailableTimes.stream().anyMatch(at ->
                at.getWeekDay().name().equals(LocalDate.ofInstant(requestedDate.toInstant(), ZoneId.of("America/Sao_Paulo")).getDayOfWeek().name()) &&
                        !requestedStartTime.isBefore(at.getStartTime()) &&
                        !requestedEndTime.isAfter(at.getEndTime())
        );

        try {
            if (!isMonitorAvailable) {
                systemMessage = "Monitor esta indisponivel para agendamentos."; // Removido acento
                return; // Adicionado return para evitar NullPointerException se o monitor estiver indisponível
            }
            scheduledAssistance = new AssistsEntity(
                    student,
                    monitor,
                    requestedDate,
                    requestedStartTime,
                    requestedEndTime
            );
            systemMessage = "Agendamento realizado com sucesso!";
        } catch (IllegalArgumentException e) {
            systemMessage = e.getMessage();
        }
    }

    @Then("o sistema agenda o atendimento") // Removido sufixo
    public void o_sistema_agenda_o_atendimento() {
        assertNotNull(scheduledAssistance, "O agendamento nao deveria ser nulo."); // Removido acento
        assertEquals(student, scheduledAssistance.getStudent());
        assertEquals(monitor, scheduledAssistance.getMonitor());
        assertEquals(requestedDate, scheduledAssistance.getDay());
        assertEquals(requestedStartTime, scheduledAssistance.getStartTime());
        assertEquals(requestedEndTime, scheduledAssistance.getEndTime());
    }

    @And("notifica o aluno com a confirmacao do agendamento") // Removido acento e sufixo
    public void notifica_o_aluno_com_a_confirmacao_do_agendamento() {
        assertEquals("Agendamento realizado com sucesso!", systemMessage);
    }

    // --- Cenário: Tentativa de agendamento com monitor indisponivel ---

    @Given("um monitor nao possui horarios disponiveis") // Removido acento e sufixo
    public void um_monitor_nao_possui_horarios_disponiveis() {
        monitor = new MonitorEntity(
                2L,
                "Carlos",
                "Lima",
                "carlos@example.com",
                "monitorpass",
                UserRole.MONITOR,
                List.of(10L),
                10L,
                new ArrayList<>()
        );
        monitorAvailableTimes.clear(); // Garante que nao ha horarios disponiveis
    }

    // O passo @When("um aluno solicita o agendamento de um atendimento") é reutilizado.

    @Then("o sistema informa que o monitor esta indisponivel para agendamentos") // Removido acento e sufixo
    public void o_sistema_informa_que_o_monitor_esta_indisponivel_para_agendamentos() {
        assertEquals("Monitor esta indisponivel para agendamentos.", systemMessage); // Removido acento
        assertNull(scheduledAssistance, "O agendamento nao deveria ter sido criado."); // Removido acento
    }
}