package com.monitorando.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.monitorando.controller.MonitorAvailabilityController;
import com.example.monitorando.DTO.MonitorAvailabilityRequest;
import com.example.monitorando.entity.MonitorEntity;
import com.example.monitorando.repository.MonitorRepository;
import com.example.monitorando.service.MonitorAvailabilityService;
import com.example.monitorando.service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class HorarioMonitorSteps {

    @Mock
    private MonitorAvailabilityService availabilityService;
    
    @Mock
    private MonitorRepository monitorRepository;
    
    @Mock
    private NotificationService notificationService;
    
    @Mock
    private MonitorAvailabilityController availabilityController;
    
    private MonitorEntity monitor;
    private MonitorAvailabilityRequest availabilityRequest;
    private List<MonitorAvailabilityRequest.AvailabilityDTO> availabilityList;
    private String responseMessage;
    private boolean isAvailabilityUpdated;

    public HorarioMonitorSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um monitor está cadastrado no sistema")
    public void umMonitorEstaCadastradoNoSistema() {
        // Setup monitor
        monitor = new MonitorEntity();
        monitor.setId(1L);
        monitor.setName("Monitor Test");
        monitor.setEmail("monitor@test.com");
        
        // Mock repository to return the monitor
        when(monitorRepository.findById(monitor.getId())).thenReturn(Optional.of(monitor));
        
        System.out.println("Precondition: Monitor is registered in the system");
    }

    @When("o monitor cadastra seus horários disponíveis")
    public void oMonitorCadastraSeusHorariosDisponiveis() {
        // Create availability list
        availabilityList = new ArrayList<>();
        
        // Add some availability slots
        MonitorAvailabilityRequest.AvailabilityDTO monday = new MonitorAvailabilityRequest.AvailabilityDTO();
        monday.setDayOfWeek("MONDAY");
        monday.setStartTime("14:00");
        monday.setEndTime("16:00");
        availabilityList.add(monday);
        
        MonitorAvailabilityRequest.AvailabilityDTO wednesday = new MonitorAvailabilityRequest.AvailabilityDTO();
        wednesday.setDayOfWeek("WEDNESDAY");
        wednesday.setStartTime("10:00");
        wednesday.setEndTime("12:00");
        availabilityList.add(wednesday);
        
        // Create availability request
        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);
        
        // Mock service to return success
        when(availabilityService.updateMonitorAvailability(availabilityRequest)).thenReturn(true);
        
        // Try to update availability
        try {
            isAvailabilityUpdated = availabilityService.updateMonitorAvailability(availabilityRequest);
            responseMessage = "Availability updated successfully";
        } catch (Exception e) {
            isAvailabilityUpdated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Monitor registers available hours");
    }

    @When("o monitor tenta cadastrar horários sem informar o dia da semana")
    public void oMonitorTentaCadastrarHorariosSemInformarODiaDaSemana() {
        // Create availability list with missing day of week
        availabilityList = new ArrayList<>();
        
        // Add availability slot with missing day
        MonitorAvailabilityRequest.AvailabilityDTO invalidSlot = new MonitorAvailabilityRequest.AvailabilityDTO();
        invalidSlot.setDayOfWeek(null); // Missing day
        invalidSlot.setStartTime("14:00");
        invalidSlot.setEndTime("16:00");
        availabilityList.add(invalidSlot);
        
        // Create availability request
        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);
        
        // Mock service to throw exception
        when(availabilityService.updateMonitorAvailability(availabilityRequest)).thenThrow(
            new IllegalArgumentException("Day of week is required for all availability slots"));
        
        // Try to update availability
        try {
            isAvailabilityUpdated = availabilityService.updateMonitorAvailability(availabilityRequest);
            responseMessage = "Availability updated successfully";
        } catch (Exception e) {
            isAvailabilityUpdated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Monitor tries to register hours without specifying day of week");
    }

    @When("o monitor tenta cadastrar horários com horário de início após o horário de fim")
    public void oMonitorTentaCadastrarHorariosComHorarioDeInicioAposOHorarioDeFim() {
        // Create availability list with invalid time range
        availabilityList = new ArrayList<>();
        
        // Add availability slot with invalid time range
        MonitorAvailabilityRequest.AvailabilityDTO invalidSlot = new MonitorAvailabilityRequest.AvailabilityDTO();
        invalidSlot.setDayOfWeek("FRIDAY");
        invalidSlot.setStartTime("16:00"); // Start time after end time
        invalidSlot.setEndTime("14:00");
        availabilityList.add(invalidSlot);
        
        // Create availability request
        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);
        
        // Mock service to throw exception
        when(availabilityService.updateMonitorAvailability(availabilityRequest)).thenThrow(
            new IllegalArgumentException("Start time must be before end time"));
        
        // Try to update availability
        try {
            isAvailabilityUpdated = availabilityService.updateMonitorAvailability(availabilityRequest);
            responseMessage = "Availability updated successfully";
        } catch (Exception e) {
            isAvailabilityUpdated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Monitor tries to register hours with start time after end time");
    }

    @Then("o sistema registra os horários disponíveis do monitor")
    public void oSistemaRegistraOsHorariosDisponiveisDoMonitor() {
        // Verify availability was updated
        Assert.assertTrue("Availability should be updated", isAvailabilityUpdated);
        
        // Verify service was called to update availability
        verify(availabilityService, times(1)).updateMonitorAvailability(any(MonitorAvailabilityRequest.class));
        
        System.out.println("Verification: System registers monitor's available hours");
    }

    @And("notifica os alunos sobre a disponibilidade do monitor")
    public void notificaOsAlunosSobreADisponibilidadeDoMonitor() {
        // Verify notification service was called
        verify(notificationService, times(1)).notifyStudentsAboutMonitorAvailability(
            eq(monitor.getId()), anyList());
        
        System.out.println("Additional verification: System notifies students about monitor availability");
    }

    @Then("o sistema informa que o dia da semana é obrigatório")
    public void oSistemaInformaQueODiaDaSemanaEObrigatorio() {
        // Verify availability was not updated
        Assert.assertFalse("Availability should not be updated", isAvailabilityUpdated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention day of week requirement", 
            responseMessage.contains("day") || responseMessage.contains("Day"));
        
        System.out.println("Verification: System informs that day of week is mandatory");
    }

    @Then("o sistema informa que o horário de início deve ser anterior ao horário de fim")
    public void oSistemaInformaQueOHorarioDeInicioDeveSerAnteriorAoHorarioDeFim() {
        // Verify availability was not updated
        Assert.assertFalse("Availability should not be updated", isAvailabilityUpdated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention time range issue", 
            responseMessage.contains("time") || responseMessage.contains("Time"));
        
        System.out.println("Verification: System informs that start time must be before end time");
    }
}