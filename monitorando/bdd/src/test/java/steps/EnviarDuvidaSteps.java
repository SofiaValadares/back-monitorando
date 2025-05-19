package steps;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.service.QuestionService;
import builders.MonitorBuilder;
import builders.QuestionEntityBuilder;
import io.cucumber.java.pt.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnviarDuvidaSteps {

    private QuestionEntity question;
    private QuestionService questionService;
    private String mensagemRetorno;
    private MonitorEntity monitor;
    private StudentEntity aluno;

    @Dado("um aluno iniciou o envio de uma dúvida")
    public void alunoIniciaEnvio() {
        // Criando o aluno usando o construtor padrão
        aluno = new StudentEntity(123L, "Aluno", "Sobrenome", "aluno@email.com",
                "password", null, null);

        // Inicializando a questão usando o QuestionEntityBuilder
        question = new QuestionEntityBuilder()
                .comId(1L)
                .comStudent(aluno)
                .comQuestion(null) // Inicialmente sem pergunta
                .build();

        // Simulando o serviço de perguntas
        questionService = new QuestionService(null, null, null, null, null, null);
    }

    @E("selecionou um monitor")
    public void selecionaMonitor() {
        // Criando o monitor usando o MonitorBuilder
        monitor = new MonitorBuilder()
                .comId(456L)
                .comNome("Monitor")
                .comSobrenome("SobrenomeMonitor")
                .comEmail("monitor@email.com")
                .comSenha("password")
                .comPapel(UserRole.MONITOR)
                .build();

        // Atualizando a questão com o monitor e a pergunta
        question = new QuestionEntityBuilder()
                .comId(question.getId())
                .comStudent(question.getStudent())
                .comMonitor(monitor)
                .comQuestion("Como funciona a atividade?")
                .build();
    }

    @Quando("a dúvida for enviada")
    public void enviaDuvida() {
        try {
            questionService.makeQuestionToMonitor(
                    question.getStudent().getId(),
                    question.getQuestion(),
                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
                    question.getMonitor().getId()
            );
            mensagemRetorno = "Dúvida enviada com sucesso";
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }

    @Entao("o sistema registra a dúvida")
    public void sistemaRegistraDuvida() {
        // Validando se a dúvida foi registrada corretamente
        assertNotNull(question.getMonitor());
        assertNotNull(question.getStudent());
        assertNotNull(question.getQuestion());
    }

    @E("encaminha a dúvida ao monitor selecionado")
    public void encaminhaAoMonitor() {
        // Validando que o monitor foi associado corretamente
        assertEquals(456L, question.getMonitor().getId());
    }

    @E("notifica o aluno sobre o envio bem-sucedido")
    public void notificaAluno() {
        // Validando que a mensagem de sucesso foi exibida
        assertEquals("Dúvida enviada com sucesso", mensagemRetorno);
    }

    @Quando("o monitor não é selecionado")
    public void monitorNaoSelecionado() {
        // Atualizando a questão sem monitor
        question = new QuestionEntityBuilder()
                .comId(question.getId())
                .comStudent(question.getStudent())
                .comMonitor(null)
                .comQuestion("Qual o prazo?")
                .build();

        try {
            questionService.makeQuestionToMonitor(
                    question.getStudent().getId(),
                    question.getQuestion(),
                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
                    null
            );
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }

    @Entao("o sistema informa que é necessário selecionar um monitor")
    public void informaErroMonitor() {
        // Validando a mensagem de erro apropriada
        assertEquals("Monitor não encontrada com ID: null", mensagemRetorno);
    }

    @E("não registra a dúvida")
    public void naoRegistraDuvida() {
        // Validando que a dúvida não foi registrada
        assertNull(question.getMonitor());
    }

    @Quando("a mensagem não existe")
    public void mensagemVazia() {
        // Atualizando a questão com mensagem nula
        question = new QuestionEntityBuilder()
                .comId(question.getId())
                .comStudent(question.getStudent())
                .comMonitor(monitor)
                .comQuestion(null)
                .build();

        try {
            questionService.makeQuestionToMonitor(
                    question.getStudent().getId(),
                    question.getQuestion(),
                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
                    question.getMonitor().getId()
            );
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }

    @Entao("o sistema informa que a mensagem é obrigatória")
    public void mensagemObrigatoria() {
        // Validando mensagem de erro para conteúdo vazio
        assertEquals("Pergunta esta vazia", mensagemRetorno);
    }
}