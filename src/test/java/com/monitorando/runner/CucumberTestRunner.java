package com.monitorando.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/monitorando/steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberTestRunner {
}