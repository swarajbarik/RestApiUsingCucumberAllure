package stepdefs;

import java.lang.reflect.InvocationTargetException;

import org.testng.IAnnotationTransformer;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;

public class AllureHooks implements IAnnotationTransformer {

	private static boolean isParentFailed = false;

	@After("@parent")
	public void beforeScenario(Scenario scenario) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		if (scenario.isFailed()) {
			isParentFailed = true;

		}
	}

	@Before("@child")
	public void beforeDivisionScenario(Scenario scenario) {
		// Check if the "Multiplication" scenario has passed
		if (isParentFailed) {
			throw new PendingException("Dependency not met: Parent scenario must pass.");
		}
	}

	@After
	public void addSeverityTag(Scenario scenario) {
		scenario.getSourceTagNames().stream().filter(tag -> tag.matches("@(critical|high|medium|low)")).findFirst()
				.ifPresent(tag -> {
					Label severityLabel = new Label().setName("severity").setValue(tag.substring(1));
					Allure.getLifecycle().updateTestCase(tc -> tc.getLabels().add(severityLabel));
				});
	}

}
