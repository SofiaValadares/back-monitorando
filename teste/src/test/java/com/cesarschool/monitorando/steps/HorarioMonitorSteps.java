package com.cesarschool.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.DTO.MonitorAvailabilityRequest;
import com.cesarschool.monitorando.apresentacao.controller.MonitorAvailabilityController;
import com.cesarschool.monitorando.apresentacao.service.MonitorAvailabilityService;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HorarioMonitorSteps {

    @Mock
    private MonitorAvailabilityService availabilityService;

    @InjectMocks
    private MonitorAvailabilityController availabilityController;

    private MonitorEntity monitor;
    private MonitorAvailabilityRequest availabilityRequest;
    private List<MonitorAvailabilityRequest.AvailabilityDTO> availabilityList;
    private ResponseEntity<?> response;

    public HorarioMonitorSteps() {
        MockitoAnnotations.openMocks(this);
        availabilityController = new MonitorAvailabilityController();
    }

    @Given("um monitor está cadastrado no sistema")
    public void umMonitorEstaCadastradoNoSistema() {
        monitor = new MonitorEntity();
        monitor.setId(1L);
        monitor.setName("Monitor Teste");
        monitor.setEmail("monitor@teste.com");
        // Aqui não precisamos simular retorno do banco pois o teste é a nível de controller
    }

    @When("o monitor cadastra seus horários disponíveis")
    public void oMonitorCadastraSeusHorariosDisponiveis() {
        availabilityList = new ArrayList<>();

        MonitorAvailabilityRequest.AvailabilityDTO seg = new MonitorAvailabilityRequest.AvailabilityDTO();
        seg.setDayOfWeek("Segunda");
        seg.setStartTime("10:00");
        seg.setEndTime("12:00");

        availabilityList.add(seg);

        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);

        doNothing().when(availabilityService).updateAvailability(any(MonitorAvailabilityRequest.class));

        response = availabilityController.updateAvailability(availabilityRequest);
    }

    @Then("o sistema registra os horários disponíveis do monitor")
    public void oSistemaRegistraOsHorariosDisponiveisDoMonitor() {
        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("Horários atualizados e alunos notificados.", response.getBody());
    }

    @When("o monitor tenta cadastrar horários sem informar o dia da semana")
    public void oMonitorTentaCadastrarHorariosSemInformarODiaDaSemana() {
        availabilityList = new ArrayList<>();

        MonitorAvailabilityRequest.AvailabilityDTO invalido = new MonitorAvailabilityRequest.AvailabilityDTO();
        invalido.setDayOfWeek(null); // inválido
        invalido.setStartTime("09:00");
        invalido.setEndTime("11:00");

        availabilityList.add(invalido);

        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);

        doThrow(new IllegalArgumentException("Dia da semana é obrigatório."))
                .when(availabilityService).updateAvailability(any(MonitorAvailabilityRequest.class));

        response = availabilityController.updateAvailability(availabilityRequest);
    }

    @Then("o sistema informa que o dia da semana é obrigatório")
    public void oSistemaInformaQueODiaDaSemanaEObrigatorio() {
        Assert.assertEquals(400, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().toString().contains("Dia da semana é obrigatório"));
    }

    @When("o monitor tenta cadastrar horários com horário de início após o horário de fim")
    public void oMonitorTentaCadastrarHorariosComHorarioDeInicioAposOHorarioDeFim() {
        availabilityList = new ArrayList<>();

        MonitorAvailabilityRequest.AvailabilityDTO invalido = new MonitorAvailabilityRequest.AvailabilityDTO();
        invalido.setDayOfWeek("Quarta");
        invalido.setStartTime("14:00");
        invalido.setEndTime("12:00");

        availabilityList.add(invalido);

        availabilityRequest = new MonitorAvailabilityRequest();
        availabilityRequest.setMonitorId(monitor.getId());
        availabilityRequest.setAvailability(availabilityList);

        doThrow(new IllegalArgumentException("Horário de início deve ser antes do horário de fim."))
                .when(availabilityService).updateAvailability(any(MonitorAvailabilityRequest.class));

        response = availabilityController.updateAvailability(availabilityRequest);
    }

    @Then("o sistema informa que o horário de início deve ser anterior ao horário de fim")
    public void oSistemaInformaQueOHorarioDeInicioDeveSerAnteriorAoHorarioDeFim() {
        Assert.assertEquals(400, response.getStatusCodeValue());
        Assert.assertTrue(response.getBody().toString().contains("Horário de início deve ser antes do horário de fim"));
    }
}
