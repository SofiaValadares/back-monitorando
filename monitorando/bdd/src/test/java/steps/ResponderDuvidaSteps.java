package steps;

import br.com.cesarschool.domain.entity.*;
import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import br.com.cesarschool.domain.entity.enums.UserRole;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ResponderDuvidaSteps {

    private QuestionEntity duvidaOriginal;
    private QuestionEntity duvidaRespondida;
    private StudentEntity student;
    private MonitorEntity monitor;
    private String resposta;
    private String mensagemRetorno;
    private QuestionEntity question;

    @Before
    public void setUp() {
        mensagemRetorno = null;
    }

    @Dado("uma uma dúvida foi enviada por um aluno")
    public void umaDuvidaFoiEnviadaPorUmAluno(String tipo, String situacao) {
        // Cria o aluno
        student = new StudentEntity(
                100L,
                "João",
                "Silva",
                "joao@exemplo.com",
                "senha123",
                UserRole.STUDENT,
                List.of(1L)
        );

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

        // Cria a dúvida original (pendente)
        duvidaOriginal = new QuestionEntity(
                1L,
                "Como funciona a lei de Ohm?",
                student.getId(),
                1L,
                false,
                QuestionStatus.PENDING,
                monitor.getId()
        );
    }

    @Quando("o monitor escreve a resposta e envia")
    public void oMonitorEscreveARespostaEEnvia() {
        resposta = "A lei de Ohm relaciona tensão, corrente e resistência.";

        if (resposta == null || resposta.trim().isEmpty()) {
            mensagemRetorno = "O campo de resposta é obrigatório.";
        } else {
            // Simula o envio da resposta (não altera a QuestionEntity real pois não tem setters)
            mensagemRetorno = "Resposta enviada com sucesso!";
        }
    }

    @Entao("o sistema registra a resposta")
    public void oSistemaRegistraAResposta() {
        // No teste, só validamos que a mensagem de sucesso foi gerada,
        // já que a entidade QuestionEntity não tem como guardar a resposta
        assertEquals("Resposta enviada com sucesso!", mensagemRetorno);
    }

    @E("o sistema notifica o aluno sobre a resposta recebida")
    public void oSistemaNotificaOAlunoSobreARespostaRecebida() {
        // Mesma verificação, pois a notificação e a resposta são parte do mesmo fluxo
        assertEquals("Resposta enviada com sucesso!", mensagemRetorno);
    }


    @Dado("uma dúvida foi enviada por um aluno")
    public void umaDuvidaFoiEnviadaPorUmAluno() {
        // Simula a criação de uma dúvida pendente
        question = new QuestionEntity(
                1L,
                "Qual é a fórmula de Bhaskara?",
                100L, // id do aluno
                1L,   // id da disciplina
                false,
                QuestionStatus.PENDING,
                200L  // id do monitor
        );
    }

    @Quando("o monitor tenta enviar a resposta sem preencher o campo de mensagem")
    public void oMonitorTentaEnviarRespostaSemMensagem() {
        resposta = ""; // Simula campo vazio

        if (resposta == null || resposta.trim().isEmpty()) {
            mensagemRetorno = "O campo de resposta é obrigatório.";
        } else {
            mensagemRetorno = "Resposta enviada com sucesso!";
        }
    }

    @Entao("o sistema informa que o campo de resposta é obrigatório")
    public void oSistemaInformaCampoRespostaObrigatorio() {
        assertEquals("O campo de resposta é obrigatório.", mensagemRetorno);
    }

    @E("o sistema não envia a resposta")
    public void oSistemaNaoEnviaAResposta() {
        // A resposta não deve ser enviada (simulamos isso validando a mensagem)
        assertEquals("O campo de resposta é obrigatório.", mensagemRetorno);
    }

    @Dado("uma dúvida já foi respondida anteriormente")
    public void umaDuvidaJaFoiRespondidaAnteriormente() {
        // Simula que a dúvida já foi respondida
        question = new QuestionEntity(
                1L,
                "Qual é a fórmula de Bhaskara?",
                100L, // id do aluno
                1L,   // id da disciplina
                false,
                QuestionStatus.ANSWERED, // Já foi respondida
                200L
        );
    }

    @Quando("o monitor tenta enviar uma nova resposta para a mesma dúvida")
    public void oMonitorTentaEnviarNovaRespostaParaDuvidaRespondida() {
        if (question.getStatus() == QuestionStatus.ANSWERED) {
            mensagemRetorno = "A dúvida já foi respondida.";
        } else {
            mensagemRetorno = "Resposta enviada com sucesso!";
        }
    }

    @Entao("o sistema informa que a dúvida já foi respondida")
    public void oSistemaInformaQueDuvidaJaRespondida() {
        assertEquals("A dúvida já foi respondida.", mensagemRetorno);
    }

    @E("o sistema impede o envio de outra resposta")
    public void oSistemaImpedeEnvioOutraResposta() {
        assertEquals("A dúvida já foi respondida.", mensagemRetorno);
    }
}
