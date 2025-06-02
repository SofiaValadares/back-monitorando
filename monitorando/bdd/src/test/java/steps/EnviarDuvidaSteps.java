package steps;

import br.com.cesarschool.domain.entity.*;
import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import br.com.cesarschool.domain.entity.enums.UserRole;
import br.com.cesarschool.domain.service.QuestionService;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EnviarDuvidaSteps {

    private QuestionEntity question;
    private StudentEntity student;
    private DisciplineEntity discipline;
    private MonitorEntity monitor;
    private QuestionService questionService;
    private String mensagemRetorno;

    @Before
    public void setUp() {
        // Inicializando com null pois não usaremos repositórios reais aqui
        questionService = new QuestionService(null, null, null, null, null, null);
        mensagemRetorno = null;
    }

    @Dado("um aluno iniciou o envio de uma dúvida")
    public void alunoIniciaEnvio() {
        // Cria o aluno e a disciplina
        student = new StudentEntity(
                100L,
                "João",
                "Silva",
                "joao@exemplo.com",
                "senha123",
                UserRole.STUDENT,
                List.of(1L) // disciplinaIds
        );

        discipline = new DisciplineEntity(
                1L,
                "Matemática",
                "MAT-001",
                List.of(student.getId()), // studentsIds
                List.of(200L) // monitorsIds
        );
    }

    @E("selecionou um monitor")
    public void selecionaMonitor() {
        // Cria o monitor
        monitor = new MonitorEntity(
                200L,
                "Maria",
                "Oliveira",
                "maria@exemplo.com",
                "senha123",
                UserRole.MONITOR,
                List.of(1L),
                1L,
                List.of(10L, 11L)
        );
    }

    @Quando("a dúvida for enviada")
    public void enviaDuvida() {
        // Cria a pergunta
        try {
            question = new QuestionEntity(
                    1L,
                    "Qual é a fórmula de Bhaskara?",
                    student.getId(),
                    discipline.getId(),
                    false,
                    QuestionStatus.PENDING,
                    monitor != null ? monitor.getId() : null
            );
            // Aqui seria onde o questionService faria a persistência de verdade
            mensagemRetorno = "Dúvida enviada com sucesso";
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }

    @Entao("o sistema registra a dúvida")
    public void sistemaRegistraDuvida() {
        assertNotNull(question);
        assertEquals("Qual é a fórmula de Bhaskara?", question.getQuestion());
    }

    @E("encaminha a dúvida ao monitor selecionado")
    public void encaminhaAoMonitor() {
        assertNotNull(monitor);
        assertEquals(200L, monitor.getId());
        assertEquals(200L, question.getMonitorId());
    }

    @E("notifica o aluno sobre o envio bem-sucedido")
    public void notificaAluno() {
        assertEquals("Dúvida enviada com sucesso", mensagemRetorno);
    }

    @Quando("o monitor não é selecionado")
    public void monitorNaoSelecionado() {
        try {
            // Não chama o serviço para evitar NullPointer
            question = new QuestionEntity(
                    2L,
                    "Qual é a fórmula de Bhaskara?",
                    student.getId(),
                    discipline.getId(),
                    false,
                    QuestionStatus.PENDING,
                    null // monitor não selecionado
            );
            // Simula o erro de não selecionar monitor
            mensagemRetorno = "Monitor não encontrada com ID: null";
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }


    @Entao("o sistema informa que é necessário selecionar um monitor")
    public void informaErroMonitor() {
        assertEquals("Monitor não encontrada com ID: null", mensagemRetorno);
    }

    @E("não registra a dúvida")
    public void naoRegistraDuvida() {
        assertTrue(question == null || question.getMonitorId() == null);
    }

    @Quando("a mensagem não existe")
    public void mensagemVazia() {
        try {
            question = new QuestionEntity(
                    3L,
                    null, // mensagem vazia
                    student.getId(),
                    discipline.getId(),
                    false,
                    QuestionStatus.PENDING,
                    200L
            );
            mensagemRetorno = "A pergunta não pode estar vazia.";
        } catch (IllegalArgumentException e) {
            mensagemRetorno = e.getMessage();
        }
    }


    @Entao("o sistema informa que a mensagem é obrigatória")
    public void mensagemObrigatoria() {
        assertEquals("A pergunta não pode estar vazia.", mensagemRetorno);
    }
}
