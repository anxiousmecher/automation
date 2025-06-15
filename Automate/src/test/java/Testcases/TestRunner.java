package Testcases;

import org.junit.runner.RunWith;

import Base.basecucumber;
import Base.basetest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature/login.feature",
    glue = {"Step","Base"},
    plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class TestRunner extends basecucumber{
}
