package com.cesarschool.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.DTO.AppointmentDTO;
import com.cesarschool.monitorando.apresentacao.service.AppointmentService;
import com.cesarschool.monitorando.dominio.entity.AppointmentEntity;
import com.cesarschool.monitorando.dominio.entity.DisciplineEntity;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import com.cesarschool.monitorando.persistencia.repository.AppointmentRepository;
import io.cucumber.java.en.*;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class AgendamentoTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentEntity appointmentEntity;

    private AppointmentDTO result;
    private Exception exception;
    private StudentEntity student;
    private MonitorEntity monitor;
    private DisciplineEntity discipline;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public AgendamentoTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um aluno válido deseja agendar com um monitor disponível em uma disciplina existente")
    public void setupAgendamentoValido() {
        student = new StudentEntity();
        student.setId(1L);

        monitor = new MonitorEntity();
        monitor.setId(2L);

        discipline = new DisciplineEntity();
        discipline.setId(3L);

        date = LocalDate.now().plusDays(1);
        startTime = LocalTime.of(10, 0);
        endTime = LocalTime.of(11, 0);

        appointmentEntity = new AppointmentEntity();
        appointmentEntity.setStudent(student);
        appointmentEntity.setMonitor(monitor);
        appointmentEntity.setDiscipline(discipline);
        appointmentEntity.setAppointmentDate(date);
        appointmentEntity.setStartTime(startTime);
        appointmentEntity.setEndTime(endTime);
        appointmentEntity.setStatus(AppointmentEntity.Status.pending_approval);
        appointmentEntity.setCreatedAt(LocalDateTime.now());
    }

    @When("o aluno envia a solicitação de agendamento")
    public void enviaSolicitacaoDeAgendamento() {
        try {
            when(appointmentService.requestAppointment(any(AppointmentEntity.class)))
                    .thenReturn(new AppointmentDTO(
                            1L,
                            student.getId(),
                            monitor.getId(),
                            discipline.getId(),
                            date,
                            startTime,
                            endTime,
                            "pending_approval",
                            LocalDateTime.now()
                    ));

            result = appointmentService.requestAppointment(appointmentEntity);

        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("o sistema deve registrar o agendamento com status pendente")
    public void verificaAgendamentoComStatusPendente() {
        Assert.assertNotNull(result);
        Assert.assertEquals("pending_approval", result.getStatus());
    }

    @Given("um monitor não possui horários disponíveis")
    public void agendamentoJaRealizadoNoMesmoHorario() {
        student = new StudentEntity();
        student.setId(1L);

        monitor = new MonitorEntity();
        monitor.setId(2L);

        discipline = new DisciplineEntity();
        discipline.setId(3L);

        date = LocalDate.now().plusDays(1);
        startTime = LocalTime.of(10, 0);
        endTime = LocalTime.of(11, 0);

        AppointmentEntity primeiro = new AppointmentEntity();
        primeiro.setStudent(student);
        primeiro.setMonitor(monitor);
        primeiro.setDiscipline(discipline);
        primeiro.setAppointmentDate(date);
        primeiro.setStartTime(startTime);
        primeiro.setEndTime(endTime);
        primeiro.setStatus(AppointmentEntity.Status.pending_approval);
        primeiro.setCreatedAt(LocalDateTime.now());

        // Simula que no momento do primeiro agendamento não havia conflitos
        when(appointmentRepository.findByMonitorIdAndAppointmentDate(monitor.getId(), date))
                .thenReturn(List.of());

        appointmentService.requestAppointment(primeiro);

        // Simula que agora já existe um agendamento salvo com esse horário
        when(appointmentRepository.findByMonitorIdAndAppointmentDate(monitor.getId(), date))
                .thenReturn(List.of(primeiro));
    }

    @When("um aluno solicita o agendamento de um atendimento")
    public void tentaSegundoAgendamentoNoMesmoHorario() {
        try {
            AppointmentEntity segundo = new AppointmentEntity();
            segundo.setStudent(student);
            segundo.setMonitor(monitor);
            segundo.setDiscipline(discipline);
            segundo.setAppointmentDate(date);
            segundo.setStartTime(startTime);
            segundo.setEndTime(endTime);
            segundo.setStatus(AppointmentEntity.Status.pending_approval);
            segundo.setCreatedAt(LocalDateTime.now());

            appointmentService.requestAppointment(segundo);
        } catch (Exception e) {
            exception = e;
        }
    }



    @Then("o sistema deve recusar o agendamento informando indisponibilidade")
    public void verificaIndisponibilidadeDoMonitor() {
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("indisponível"));
    }
}
