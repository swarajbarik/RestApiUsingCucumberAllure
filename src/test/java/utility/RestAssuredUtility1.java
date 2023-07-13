package utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtility1 {
	private String baseUrl;
	private Map<String, Object> defaultHeaders;
	private URI url;

	public RestAssuredUtility1(String baseUrl) {
		this.baseUrl = baseUrl;
		defaultHeaders = new HashMap<String, Object>();
		defaultHeaders.put("Content-Type", "application/json");
	}

	public URI buildUrl(String endpoint) throws URISyntaxException {
		URI url = new URI(baseUrl + endpoint);
		this.url = url;
		return url;
	}

	public Response sendRequest(String method, Map<String, Object> pathParams, Map<String, Object> queryParams,
			Map<String, Object> headers, String body) {
		RequestSpecification requestSpecBuilder;
		if (headers != null) {
			requestSpecBuilder = RestAssured.given().headers(headers);
		} else {
			requestSpecBuilder = RestAssured.given().headers(defaultHeaders);
		}

		if (queryParams != null && !queryParams.isEmpty()) {
			requestSpecBuilder.queryParams(queryParams);
		}

		if (pathParams != null && !pathParams.isEmpty()) {
			requestSpecBuilder.pathParams(pathParams);
		}

		if (body != null) {
			requestSpecBuilder.body(body);
		}

		Response response;
		switch (method) {
		case "GET":
			response = requestSpecBuilder.get(url);
			break;
		case "POST":
			response = requestSpecBuilder.post(url);
			break;
		case "PUT":
			response = requestSpecBuilder.put(url);
			break;
		case "DELETE":
			response = requestSpecBuilder.delete(url);
			break;
		default:
			throw new IllegalArgumentException("Invalid HTTP method: " + method);
		}

		return response;
	}
}
