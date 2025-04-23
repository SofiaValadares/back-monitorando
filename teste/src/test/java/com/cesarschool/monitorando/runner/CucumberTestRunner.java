package com.cesarschool.monitorando.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.cesarschool.monitorando.steps",
    plugin = {"pretty", "html:target/cucumber-reports"},
    tags = "@relato"
)
public class CucumberTestRunner {
    // This class is empty as it's just a runner
}