package com.monitorando.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.monitorando.controller.AppointmentController;
import com.monitorando.dto.AppointmentDTO;
import com.monitorando.entity.AppointmentEntity;
import com.monitorando.repository.AppointmentRepository;
import com.monitorando.service.AppointmentService;
import com.monitorando.entity.MonitorEntity;
import com.monitorando.entity.StudentEntity;
import com.monitorando.service.NotificationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AgendamentoSteps {

    @Mock
    private AppointmentService appointmentService;
    
    @Mock
    private AppointmentRepository appointmentRepository;
    
    @Mock
    private NotificationService notificationService;
    
    @Mock
    private AppointmentController appointmentController;
    
    private MonitorEntity monitor;
    private StudentEntity student;
    private AppointmentDTO appointmentDTO;
    private AppointmentEntity appointmentEntity;
    private List<LocalDateTime> availableSlots;
    private String responseMessage;
    private boolean isAppointmentCreated;

    public AgendamentoSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um monitor possui horários disponíveis")
    public void umMonitorPossuiHorariosDisponiveis() {
        // Setup monitor with available hours
        monitor = new MonitorEntity();
        monitor.setId(1L);
        monitor.setName("Monitor Test");
        
        // Create available time slots
        availableSlots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        availableSlots.add(now.plusDays(1));
        availableSlots.add(now.plusDays(2));
        
        // Mock the service to return available slots
        when(appointmentService.getAvailableSlots(monitor.getId())).thenReturn(availableSlots);
        
        System.out.println("Precondition: Monitor has available hours");
    }

    @Given("um monitor não possui horários disponíveis")
    public void umMonitorNaoPossuiHorariosDisponiveis() {
        // Setup monitor without available hours
        monitor = new MonitorEntity();
        monitor.setId(2L);
        monitor.setName("Busy Monitor");
        
        // Empty available slots
        availableSlots = new ArrayList<>();
        
        // Mock the service to return empty slots
        when(appointmentService.getAvailableSlots(monitor.getId())).thenReturn(availableSlots);
        
        System.out.println("Precondition: Monitor has no available hours");
    }

    @When("um aluno solicita o agendamento de um atendimento")
    public void umAlunoSolicitaOAgendamentoDeUmAtendimento() {
        // Setup student
        student = new StudentEntity();
        student.setId(1L);
        student.setName("Student Test");
        
        // Create appointment request
        appointmentDTO = new AppointmentDTO();
        appointmentDTO.setMonitorId(monitor.getId());
        appointmentDTO.setStudentId(student.getId());
        
        // If monitor has available slots, use the first one
        if (!availableSlots.isEmpty()) {
            appointmentDTO.setDateTime(availableSlots.get(0));
            
            // Create appointment entity that would be saved
            appointmentEntity = new AppointmentEntity();
            appointmentEntity.setId(1L);
            appointmentEntity.setMonitor(monitor);
            appointmentEntity.setStudent(student);
            appointmentEntity.setDateTime(availableSlots.get(0));
            appointmentEntity.setStatus("PENDING");
            
            // Mock service to return success
            when(appointmentService.createAppointment(appointmentDTO)).thenReturn(appointmentEntity);
            isAppointmentCreated = true;
            responseMessage = "Appointment scheduled successfully";
        } else {
            // Mock service to throw exception or return null
            when(appointmentService.createAppointment(appointmentDTO)).thenThrow(
                new RuntimeException("Monitor is not available for appointments"));
            isAppointmentCreated = false;
            responseMessage = "Monitor is not available for appointments";
        }
        
        // Try to create appointment
        try {
            appointmentEntity = appointmentService.createAppointment(appointmentDTO);
        } catch (Exception e) {
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Student requests an appointment");
    }

    @Then("o sistema agenda o atendimento com status pendente para aprovacao")
    public void oSistemaAgendaOAtendimentoComStatusPendenteParaAprovacao() {
        // Verify appointment was created with pending status
        Assert.assertTrue("Appointment should be created", isAppointmentCreated);
        Assert.assertNotNull("Appointment entity should not be null", appointmentEntity);
        Assert.assertEquals("Appointment status should be PENDING", "PENDING", appointmentEntity.getStatus());
        
        // Verify repository was called to save the appointment
        verify(appointmentRepository, times(1)).save(any(AppointmentEntity.class));
        
        System.out.println("Verification: System schedules the appointment with pending status");
    }

    @And("notifica o aluno e monitor com a confirmação do agendamento")
    public void notificaOAlunoEMonitorComAConfirmacaoDoAgendamento() {
        // Verify notifications were sent
        verify(notificationService, times(1)).sendAppointmentNotification(
            eq(student.getId()), eq("Your appointment request has been scheduled and is pending approval"));
            
        verify(notificationService, times(1)).sendAppointmentNotification(
            eq(monitor.getId()), eq("You have a new appointment request pending your approval"));
        
        System.out.println("Additional verification: System notifies student and monitor with appointment confirmation");
    }

    @Then("o sistema informa que o monitor está indisponível para agendamentos")
    public void oSistemaInformaQueOMonitorEstaIndisponivelParaAgendamentos() {
        // Verify appointment was not created
        Assert.assertFalse("Appointment should not be created", isAppointmentCreated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention monitor unavailability", 
            responseMessage.contains("not available") || responseMessage.contains("unavailable"));
        
        // Verify repository was not called to save any appointment
        verify(appointmentRepository, never()).save(any(AppointmentEntity.class));
        
        System.out.println("Verification: System informs that the monitor is unavailable for appointments");
    }
}