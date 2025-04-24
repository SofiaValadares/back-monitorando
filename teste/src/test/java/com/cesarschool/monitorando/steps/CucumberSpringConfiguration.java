package com.cesarschool.monitorando.steps;

import com.cesarschool.monitorando.apresentacao.MonitorandoApresentacaoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MonitorandoApresentacaoApplication.class)
public class CucumberSpringConfiguration {
}
