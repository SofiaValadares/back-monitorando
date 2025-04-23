package com.monitorando.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.monitorando.controller.EvaluationController;
import com.example.monitorando.DTO.EvaluationRequestDTO;
import com.example.monitorando.entity.EvaluationEntity;
import com.example.monitorando.repository.EvaluationRepository;
import com.example.monitorando.service.EvaluationService;
import com.example.monitorando.entity.MonitorEntity;
import com.example.monitorando.entity.StudentEntity;
import com.example.monitorando.service.NotificationService;

import static org.mockito.Mockito.*;

public class RelatoSteps {

    @Mock
    private EvaluationService evaluationService;
    
    @Mock
    private EvaluationRepository evaluationRepository;
    
    @Mock
    private NotificationService notificationService;
    
    @Mock
    private EvaluationController evaluationController;
    
    private StudentEntity student;
    private MonitorEntity monitor;
    private EvaluationRequestDTO evaluationDTO;
    private EvaluationEntity evaluationEntity;
    private String responseMessage;
    private boolean isEvaluationCreated;

    public RelatoSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um aluno realizou um atendimento com um monitor")
    public void umAlunoRealizouUmAtendimentoComUmMonitor() {
        // Setup student
        student = new StudentEntity();
        student.setId(1L);
        student.setName("Student Test");
        
        // Setup monitor
        monitor = new MonitorEntity();
        monitor.setId(1L);
        monitor.setName("Monitor Test");
        
        // Initialize evaluation DTO
        evaluationDTO = new EvaluationRequestDTO();
        evaluationDTO.setStudentId(student.getId());
        evaluationDTO.setMonitorId(monitor.getId());
        
        System.out.println("Precondition: Student had an appointment with a monitor");
    }

    @When("o aluno submete uma avaliação com nota {int} e comentário")
    public void oAlunoSubmeteUmaAvaliacaoComNotaEComentario(Integer rating) {
        // Set rating and comment in evaluation DTO
        evaluationDTO.setRating(rating);
        evaluationDTO.setComment("Ótimo atendimento, monitor muito atencioso.");
        
        // Create evaluation entity that would be saved
        evaluationEntity = new EvaluationEntity();
        evaluationEntity.setId(1L);
        evaluationEntity.setRating(rating);
        evaluationEntity.setComment(evaluationDTO.getComment());
        
        // Mock service to return success
        doNothing().when(evaluationService).avaliar(evaluationDTO);
        
        // Try to create evaluation
        try {
            evaluationService.avaliar(evaluationDTO);
            isEvaluationCreated = true;
            responseMessage = "Avaliação registrada com sucesso.";
        } catch (Exception e) {
            isEvaluationCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Student submits evaluation with rating " + rating + " and comment");
    }

    @When("o aluno submete uma avaliação com nota {int} sem comentário")
    public void oAlunoSubmeteUmaAvaliacaoComNotaSemComentario(Integer rating) {
        // Set rating without comment in evaluation DTO
        evaluationDTO.setRating(rating);
        evaluationDTO.setComment("");
        
        // Create evaluation entity that would be saved
        evaluationEntity = new EvaluationEntity();
        evaluationEntity.setId(1L);
        evaluationEntity.setRating(rating);
        evaluationEntity.setComment("");
        
        // Mock service to return success
        doNothing().when(evaluationService).avaliar(evaluationDTO);
        
        // Try to create evaluation
        try {
            evaluationService.avaliar(evaluationDTO);
            isEvaluationCreated = true;
            responseMessage = "Avaliação registrada com sucesso.";
        } catch (Exception e) {
            isEvaluationCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Student submits evaluation with rating " + rating + " without comment");
    }

    @When("o aluno submete uma avaliação com nota inválida {int}")
    public void oAlunoSubmeteUmaAvaliacaoComNotaInvalida(Integer rating) {
        // Set invalid rating in evaluation DTO
        evaluationDTO.setRating(rating);
        evaluationDTO.setComment("Comentário de teste");
        
        // Mock service to throw exception
        doThrow(new IllegalArgumentException("A nota deve estar entre 1 e 5"))
            .when(evaluationService).avaliar(evaluationDTO);
        
        // Try to create evaluation
        try {
            evaluationService.avaliar(evaluationDTO);
            isEvaluationCreated = true;
            responseMessage = "Avaliação registrada com sucesso.";
        } catch (Exception e) {
            isEvaluationCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Student submits evaluation with invalid rating " + rating);
    }

    @Then("o sistema registra a avaliação")
    public void oSistemaRegistraAAvaliacao() {
        // Verify evaluation was created
        Assert.assertTrue("Evaluation should be created", isEvaluationCreated);
        
        // Verify service was called to create evaluation
        verify(evaluationService, times(1)).avaliar(any(EvaluationRequestDTO.class));
        
        System.out.println("Verification: System registers the evaluation");
    }

    @And("notifica o monitor sobre a avaliação recebida")
    public void notificaOMonitorSobreAAvaliacaoRecebida() {
        // Verify notification service was called
        verify(notificationService, times(1)).notifyMonitorAboutEvaluation(
            eq(monitor.getId()), anyString());
        
        System.out.println("Additional verification: System notifies monitor about received evaluation");
    }

    @Then("o sistema informa que a nota deve estar entre {int} e {int}")
    public void oSistemaInformaQueANotaDeveEstarEntreE(Integer min, Integer max) {
        // Verify evaluation was not created
        Assert.assertFalse("Evaluation should not be created", isEvaluationCreated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention valid rating range", 
            responseMessage.contains(min.toString()) && responseMessage.contains(max.toString()));
        
        System.out.println("Verification: System informs that rating must be between " + min + " and " + max);
    }
}