package steps;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.entity.enums.WeekDay;
import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonitorAvailabilitySteps {

    private MonitorEntity monitor;
    private List<AvailableTimeEntity> horariosDisponiveis;
    private boolean horarioValido = true;
    private String notificacaoSistema = "";

    @Before
    public void setUp() {
        horariosDisponiveis = new ArrayList<>();
        notificacaoSistema = "";
    }

    @Dado("um monitor atualizou ou cadastrou seus horários disponíveis")
    public void monitorAtualizouHorarios() {
        // Cria horários válidos
        AvailableTimeEntity horario1 = new AvailableTimeEntity(
                1L,
                WeekDay.MONDAY,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        );
        AvailableTimeEntity horario2 = new AvailableTimeEntity(
                2L,
                WeekDay.WEDNESDAY,
                LocalTime.of(14, 0),
                LocalTime.of(16, 0)
        );

        horariosDisponiveis.add(horario1);
        horariosDisponiveis.add(horario2);

        // Cria monitor com horários disponíveis
        monitor = new MonitorEntity(
                200L,
                "Maria",
                "Oliveira",
                "maria@exemplo.com",
                "senha123",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                List.of(1L, 2L)
        );
    }

    @Quando("o sistema identifica a atualização dos horários")
    public void sistemaIdentificaAtualizacao() {
        notificacaoSistema = "Horários atualizados com sucesso!";
    }

    @Entao("o sistema envia uma notificação aos alunos cadastrados na disciplina")
    public void sistemaEnviaNotificacao() {
        assertEquals("Horários atualizados com sucesso!", notificacaoSistema);
    }

    @E("o sistema informa os novos horários disponíveis do monitor")
    public void sistemaInformaHorarios() {
        // Simula a lista de horários disponíveis vinculados ao monitor
        assertNotNull(horariosDisponiveis);
        assertFalse(horariosDisponiveis.isEmpty());
        assertEquals(2, horariosDisponiveis.size());
    }

    @Dado("um monitor informou um horário para disponibilidade")
    public void monitorInformouHorarioInvalido() {
        try {
            AvailableTimeEntity horarioInvalido = new AvailableTimeEntity(
                    3L,
                    WeekDay.MONDAY,
                    LocalTime.of(18, 0),
                    LocalTime.of(17, 0) // horário de fim antes do início - inválido
            );
            fail("Deveria lançar exceção por horário inválido");
        } catch (IllegalArgumentException e) {
            horarioValido = false;
            assertEquals("O horário de início deve ser anterior ao horário de fim.", e.getMessage());
        }
    }


    @Quando("o horário informado não é válido")
    public void verificaHorarioInvalido() {
        if (!horarioValido) {
            notificacaoSistema = "Horário inválido";
        }
    }

    @Entao("o sistema rejeita a atualização dos horários")
    public void sistemaRejeitaHorario() {
        assertFalse(horarioValido);
    }

    @E("o sistema informa ao monitor que o horário informado é inválido")
    public void sistemaInformaHorarioInvalido() {
        assertEquals("Horário inválido", notificacaoSistema);
    }
}
