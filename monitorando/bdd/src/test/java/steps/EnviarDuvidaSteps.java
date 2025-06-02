//package steps;
//
//import br.com.cesarschool.domain.entity.QuestionEntity;
//import br.com.cesarschool.domain.service.QuestionService;
//import io.cucumber.java.Before;
//import io.cucumber.java.pt.*;
//import steps.builders.BasicQuestionBuilder;
//import steps.director.QuestionDirector;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class EnviarDuvidaSteps {
//
//    private QuestionEntity question;
//    private QuestionService questionService;
//    private String mensagemRetorno;
//    private BasicQuestionBuilder questionBuilder;
//    private QuestionDirector director;
//
//    @Before
//    public void setUp() {
//        questionBuilder = new BasicQuestionBuilder();
//        director = new QuestionDirector(questionBuilder);
//        questionService = new QuestionService(null, null, null, null, null, null);
//        mensagemRetorno = null;
//    }
//
//    @Dado("um aluno iniciou o envio de uma dúvida")
//    public void alunoIniciaEnvio() {
//        director.constructQuestionWithStudentOnly();
//        question = questionBuilder.getQuestionEntity();
//    }
//
//    @E("selecionou um monitor")
//    public void selecionaMonitor() {
//        director.constructQuestionWithStudentAndMonitor();
//        question = questionBuilder.getQuestionEntity();
//    }
//
//    @Quando("a dúvida for enviada")
//    public void enviaDuvida() {
//        try {
//            questionService.makeQuestionToMonitor(
//                    question.getStudent().getId(),
//                    question.getQuestion(),
//                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
//                    question.getMonitor().getId()
//            );
//            mensagemRetorno = "Dúvida enviada com sucesso";
//        } catch (IllegalArgumentException e) {
//            mensagemRetorno = e.getMessage();
//        }
//    }
//
//    @Entao("o sistema registra a dúvida")
//    public void sistemaRegistraDuvida() {
//        assertNotNull(question.getMonitor());
//        assertNotNull(question.getStudent());
//        assertNotNull(question.getQuestion());
//    }
//
//    @E("encaminha a dúvida ao monitor selecionado")
//    public void encaminhaAoMonitor() {
//        assertEquals(200L, question.getMonitor().getId()); // ID vindo do builder
//    }
//
//    @E("notifica o aluno sobre o envio bem-sucedido")
//    public void notificaAluno() {
//        assertEquals("Dúvida enviada com sucesso", mensagemRetorno);
//    }
//
//    @Quando("o monitor não é selecionado")
//    public void monitorNaoSelecionado() {
//        // Reutilizando o builder sem construir o monitor
//        questionBuilder = new BasicQuestionBuilder();
//        questionBuilder.buildId();
//        questionBuilder.buildStudent();
//        questionBuilder.buildQuestion();
//        questionBuilder.buildDiscipline();
//        questionBuilder.buildIsPublic();
//        questionBuilder.buildStatus();
//        question = questionBuilder.getQuestionEntity();
//
//        try {
//            questionService.makeQuestionToMonitor(
//                    question.getStudent().getId(),
//                    question.getQuestion(),
//                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
//                    null
//            );
//        } catch (IllegalArgumentException e) {
//            mensagemRetorno = e.getMessage();
//        }
//    }
//
//    @Entao("o sistema informa que é necessário selecionar um monitor")
//    public void informaErroMonitor() {
//        assertEquals("Monitor não encontrada com ID: null", mensagemRetorno);
//    }
//
//    @E("não registra a dúvida")
//    public void naoRegistraDuvida() {
//        assertNull(question.getMonitor());
//    }
//
//    @Quando("a mensagem não existe")
//    public void mensagemVazia() {
//        questionBuilder = new BasicQuestionBuilder();
//        questionBuilder.buildId();
//        questionBuilder.buildStudent();
//        questionBuilder.buildDiscipline();
//        questionBuilder.buildIsPublic();
//        questionBuilder.buildStatus();
//        questionBuilder.buildMonitor(); // monta o monitor
//        // não chama buildQuestion() para simular mensagem nula
//
//        question = questionBuilder.getQuestionEntity();
//
//        try {
//            questionService.makeQuestionToMonitor(
//                    question.getStudent().getId(),
//                    null,
//                    question.getDiscipline() != null ? question.getDiscipline().getId() : null,
//                    question.getMonitor().getId()
//            );
//        } catch (IllegalArgumentException e) {
//            mensagemRetorno = e.getMessage();
//        }
//    }
//
//    @Entao("o sistema informa que a mensagem é obrigatória")
//    public void mensagemObrigatoria() {
//        assertEquals("Pergunta esta vazia", mensagemRetorno);
//    }
//}
