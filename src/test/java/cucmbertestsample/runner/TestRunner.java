package cucmbertestsample.runner;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Test
@CucumberOptions(features = { "classpath:features" }, glue = "stepdefs", plugin = { "pretty", }

)
public class TestRunner extends AbstractTestNGCucumberTests {

}
