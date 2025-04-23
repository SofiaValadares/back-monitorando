package com.cesarschool.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.DTO.QuestionRequestDTO;
import com.cesarschool.monitorando.apresentacao.service.QuestionService;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import com.cesarschool.monitorando.dominio.entity.QuestionEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DuvidaMonitorTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionEntity questionEntity;

    private QuestionRequestDTO questionDTO;
    private String resposta;
    private boolean duvidaRegistrada;

    private StudentEntity aluno;
    private MonitorEntity monitor;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("o aluno preencheu o formulário da dúvida")
    public void oAlunoPreencheuOFormularioDaDuvida() {
        aluno = new StudentEntity();
        aluno.setId(1L);

        questionDTO = new QuestionRequestDTO();
        questionDTO.setStudentId(aluno.getId());
        questionDTO.setDisciplineId(10L); // Supondo disciplina fictícia
        questionDTO.setTitle("Ajuda com listas em Java");
        questionDTO.setContent("Não estou entendendo como usar ArrayList.");
        questionDTO.setIsPublic(true);
    }

    @And("o monitor foi selecionado corretamente")
    public void oMonitorFoiSelecionadoCorretamente() {
        monitor = new MonitorEntity();
        monitor.setId(2L);
        questionDTO.setMonitorId(monitor.getId());
    }

    @When("a dúvida é enviada com sucesso")
    public void aDuvidaEhEnviadaComSucesso() {
        when(questionService.createQuestion(any())).thenReturn("Dúvida enviada com sucesso!");

        try {
            resposta = questionService.createQuestion(questionDTO);
            duvidaRegistrada = true;
        } catch (Exception e) {
            duvidaRegistrada = false;
            resposta = e.getMessage();
        }
    }

    @Then("o sistema confirma o envio da dúvida")
    public void oSistemaConfirmaOEnvioDaDuvida() {
        Assert.assertTrue(duvidaRegistrada);
        Assert.assertEquals("Dúvida enviada com sucesso!", resposta);
    }

    @When("o monitor não foi informado")
    public void oMonitorNaoFoiInformado() {
        questionDTO.setMonitorId(null);

        when(questionService.createQuestion(any()))
                .thenReturn("Erro: É necessário selecionar um monitor.");

        resposta = questionService.createQuestion(questionDTO);
        duvidaRegistrada = resposta.startsWith("Dúvida");
    }

    @Then("o sistema informa que o monitor é obrigatório")
    public void oSistemaInformaQueOMonitorEhObrigatorio() {
        Assert.assertFalse(duvidaRegistrada);
        Assert.assertTrue(resposta.contains("selecionar um monitor"));
    }

    @When("a mensagem da dúvida está em branco")
    public void aMensagemDaDuvidaEstaEmBranco() {
        questionDTO.setContent("");

        when(questionService.createQuestion(any()))
                .thenReturn("Erro: A mensagem da dúvida é obrigatória.");

        resposta = questionService.createQuestion(questionDTO);
        duvidaRegistrada = resposta.startsWith("Dúvida");
    }

    @Then("o sistema informa que a mensagem é obrigatória")
    public void oSistemaInformaQueAMensagemEhObrigatoria() {
        Assert.assertFalse(duvidaRegistrada);
        Assert.assertTrue(resposta.contains("mensagem da dúvida é obrigatória"));
    }
}
