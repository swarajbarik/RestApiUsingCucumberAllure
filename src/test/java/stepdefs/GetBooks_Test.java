package stepdefs;

import java.net.URISyntaxException;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utility.RestAssuredUtility;

public class GetBooks_Test {
	RestAssuredUtility restAssuredUtility = new RestAssuredUtility("https://demoqa.com/BookStore/v1/");
	Response response;
	@Given("i have the end point {string}")
	public void i_have_the_end_point(String endPoint) throws URISyntaxException {
		restAssuredUtility.buildUrl(endPoint);
	}

	@When("i am executing the {string} method")
	public void i_am_executing_the_method(String string) {
		response = restAssuredUtility.sendRequest("GET", null, null, null, null);
	}

	@Then("i should see the status code as {string}")
	public void i_should_see_the_status_code_as(String res) {
		System.out.println(response.statusCode());
		Assert.assertEquals("200", res);
	}

	@Then("the response should contain the below details")
	public void the_response_should_contain_the_below_details(DataTable dataTable) {
		System.out.println(response.asString());
	}

}
