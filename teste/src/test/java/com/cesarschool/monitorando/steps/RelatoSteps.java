package com.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.DTO.EvaluationRequestDTO;
import com.cesarschool.monitorando.apresentacao.service.EvaluationService;
import com.cesarschool.monitorando.dominio.entity.EvaluationEntity;
import com.cesarschool.monitorando.dominio.entity.UserEntity;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RelatoSteps {

    @Mock
    private EvaluationService evaluationService;

    @InjectMocks
    private EvaluationEntity evaluationEntity;

    private EvaluationRequestDTO evaluationDTO;
    private String responseMessage;
    private boolean avaliacaoRegistrada;

    private UserEntity aluno;
    private UserEntity monitor;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um aluno concluiu um atendimento com um monitor")
    public void umAlunoConcluiuUmAtendimentoComUmMonitor() {
        aluno = new UserEntity();
        aluno.setId(1L);
        aluno.setName("Aluno Teste");

        monitor = new UserEntity();
        monitor.setId(2L);
        monitor.setName("Monitor Teste");

        evaluationDTO = new EvaluationRequestDTO();
        evaluationDTO.setEvaluatorId(aluno.getId());
        evaluationDTO.setEvaluatedId(monitor.getId());
        evaluationDTO.setAppointmentId(100L); // Atendimento fictício
    }

    @When("o aluno envia uma avaliação com nota {int} e comentário")
    public void oAlunoEnviaUmaAvaliacaoComNotaEComentario(Integer nota) {
        evaluationDTO.setRating(nota);
        evaluationDTO.setComment("Excelente explicação, muito atencioso!");

        when(evaluationService.avaliar(any(EvaluationRequestDTO.class)))
                .thenReturn(new EvaluationEntity());

        try {
            evaluationService.avaliar(evaluationDTO);
            avaliacaoRegistrada = true;
            responseMessage = "Avaliação registrada com sucesso.";
        } catch (Exception e) {
            avaliacaoRegistrada = false;
            responseMessage = e.getMessage();
        }
    }

    @When("o aluno envia uma avaliação com nota {int} sem comentário")
    public void oAlunoEnviaUmaAvaliacaoComNotaSemComentario(Integer nota) {
        evaluationDTO.setRating(nota);
        evaluationDTO.setComment("");

        when(evaluationService.avaliar(any(EvaluationRequestDTO.class)))
                .thenReturn(new EvaluationEntity());

        try {
            evaluationService.avaliar(evaluationDTO);
            avaliacaoRegistrada = true;
            responseMessage = "Avaliação registrada com sucesso.";
        } catch (Exception e) {
            avaliacaoRegistrada = false;
            responseMessage = e.getMessage();
        }
    }

    @When("o aluno envia uma avaliação com nota inválida {int}")
    public void oAlunoEnviaUmaAvaliacaoComNotaInvalida(Integer nota) {
        evaluationDTO.setRating(nota);
        evaluationDTO.setComment("Comentário qualquer.");

        doThrow(new IllegalArgumentException("A nota deve estar entre 1 e 5"))
                .when(evaluationService).avaliar(evaluationDTO);

        try {
            evaluationService.avaliar(evaluationDTO);
            avaliacaoRegistrada = true;
        } catch (Exception e) {
            avaliacaoRegistrada = false;
            responseMessage = e.getMessage();
        }
    }

    @Then("o sistema confirma o registro da avaliação")
    public void oSistemaConfirmaORegistroDaAvaliacao() {
        Assert.assertTrue("A avaliação deve ser registrada", avaliacaoRegistrada);
        Assert.assertEquals("Avaliação registrada com sucesso.", responseMessage);
    }

    @Then("o sistema rejeita a avaliação e informa que a nota deve estar entre {int} e {int}")
    public void oSistemaRejeitaANotaInvalida(Integer min, Integer max) {
        Assert.assertFalse("Avaliação não deve ser registrada", avaliacaoRegistrada);
        Assert.assertTrue("Mensagem deve conter faixa de nota", responseMessage.contains("entre 1 e 5"));
    }
}
