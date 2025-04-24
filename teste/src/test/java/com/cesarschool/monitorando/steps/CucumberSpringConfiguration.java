package com.cesarschool.monitorando.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringConfiguration {
    // Configuração necessária para permitir que Cucumber integre com o contexto do Spring Boot
}
