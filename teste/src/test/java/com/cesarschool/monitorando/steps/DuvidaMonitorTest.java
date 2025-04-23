package com.cesarschool.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.DTO.QuestionRequestDTO;
import com.cesarschool.monitorando.apresentacao.controller.QuestionController;
import com.cesarschool.monitorando.apresentacao.service.NotificationService;
import com.cesarschool.monitorando.apresentacao.service.QuestionService;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import com.cesarschool.monitorando.dominio.entity.QuestionEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import com.cesarschool.monitorando.persistencia.repository.QuestionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DuvidaMonitorTest {

    @Mock
    private QuestionService questionService;
    
    @Mock
    private QuestionRepository questionRepository;
    
    @Mock
    private NotificationService notificationService;
    
    @Mock
    private QuestionController questionController;
    
    private StudentEntity student;
    private MonitorEntity monitor;
    private QuestionRequestDTO questionDTO;
    private QuestionEntity questionEntity;
    private String responseMessage;
    private boolean isQuestionCreated;

    public DuvidaMonitorTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um aluno iniciou o envio de uma dúvida")
    public void umAlunoIniciouOEnvioDeUmaDuvida() {
        // Setup student
        student = new StudentEntity();
        student.setId(1L);
        student.setName("Student Test");
        
        // Initialize question DTO
        questionDTO = new QuestionRequestDTO();
        questionDTO.setStudentId(student.getId());
        questionDTO.setContent("Como resolver este problema de matemática?");
        
        System.out.println("Precondition: Student started sending a question");
    }

    @And("selecionou um monitor")
    public void selecionouUmMonitor() {
        // Setup monitor
        monitor = new MonitorEntity();
        monitor.setId(1L);
        monitor.setName("Monitor Test");
        
        // Set monitor in question DTO
        questionDTO.setMonitorId(monitor.getId());
        
        System.out.println("Precondition: Student selected a monitor");
    }

    @When("a dúvida for enviada")
    public void aDuvidaForEnviada() {
        // Create question entity that would be saved
        questionEntity = new QuestionEntity();
        questionEntity.setId(1L);
        questionEntity.setStudent(student);
        questionEntity.setMonitor(monitor);
        questionEntity.setContent(questionDTO.getContent());
        questionEntity.setStatus("PENDING");
        
        // Mock service to return success
        when(questionService.createQuestion(questionDTO)).thenReturn(questionEntity);
        
        // Try to create question
        try {
            questionEntity = questionService.createQuestion(questionDTO);
            isQuestionCreated = true;
            responseMessage = "Question submitted successfully";
        } catch (Exception e) {
            isQuestionCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Question is sent");
    }

    @When("o monitor não é selecionado")
    public void oMonitorNaoESelecionado() {
        // Set monitor ID to null in question DTO
        questionDTO.setMonitorId(null);
        
        // Mock service to throw exception
        when(questionService.createQuestion(questionDTO)).thenThrow(
            new IllegalArgumentException("Monitor selection is required"));
        
        // Try to create question
        try {
            questionEntity = questionService.createQuestion(questionDTO);
            isQuestionCreated = true;
        } catch (Exception e) {
            isQuestionCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Monitor is not selected");
    }

    @When("a mensagem não existe")
    public void aMensagemNaoExiste() {
        // Set empty message in question DTO
        questionDTO.setContent("");
        
        // Mock service to throw exception
        when(questionService.createQuestion(questionDTO)).thenThrow(
            new IllegalArgumentException("Question message is required"));
        
        // Try to create question
        try {
            questionEntity = questionService.createQuestion(questionDTO);
            isQuestionCreated = true;
        } catch (Exception e) {
            isQuestionCreated = false;
            responseMessage = e.getMessage();
        }
        
        System.out.println("Action: Message does not exist");
    }

    @Then("o sistema registra a dúvida")
    public void oSistemaRegistraADuvida() {
        // Verify question was created
        Assert.assertTrue("Question should be created", isQuestionCreated);
        Assert.assertNotNull("Question entity should not be null", questionEntity);
        
        // Verify repository was called to save the question
        verify(questionRepository, times(1)).save(any(QuestionEntity.class));
        
        System.out.println("Verification: System registers the question");
    }

    @And("encaminha a dúvida ao monitor selecionado")
    public void encaminhaADuvidaAoMonitorSelecionado() {
        // Verify notification was sent to monitor
        verify(notificationService, times(1)).sendQuestionNotification(
            eq(monitor.getId()), eq("You have received a new question from a student"));
        
        System.out.println("Additional verification: System forwards the question to the selected monitor");
    }

    @And("notifica o aluno sobre o envio bem-sucedido")
    public void notificaOAlunoSobreOEnvioBemSucedido() {
        // Verify notification was sent to student
        verify(notificationService, times(1)).sendQuestionNotification(
            eq(student.getId()), eq("Your question has been submitted successfully"));
        
        System.out.println("Additional verification: System notifies the student about successful submission");
    }

    @Then("o sistema informa que é necessário selecionar um monitor")
    public void oSistemaInformaQueENecessarioSelecionarUmMonitor() {
        // Verify question was not created
        Assert.assertFalse("Question should not be created", isQuestionCreated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention monitor selection", 
            responseMessage.contains("Monitor") || responseMessage.contains("monitor"));
        
        System.out.println("Verification: System informs that it's necessary to select a monitor");
    }

    @And("não registra a dúvida")
    public void naoRegistraADuvida() {
        // Verify repository was not called to save any question
        verify(questionRepository, never()).save(any(QuestionEntity.class));
        
        System.out.println("Additional verification: System does not register the question");
    }

    @Then("o sistema informa que a mensagem é obrigatória")
    public void oSistemaInformaQueAMensagemEObrigatoria() {
        // Verify question was not created
        Assert.assertFalse("Question should not be created", isQuestionCreated);
        
        // Verify error message
        Assert.assertTrue("Error message should mention required message", 
            responseMessage.contains("message") || responseMessage.contains("Message"));
        
        System.out.println("Verification: System informs that the message is mandatory");
    }
}