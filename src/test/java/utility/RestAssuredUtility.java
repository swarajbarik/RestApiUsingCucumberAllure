package utility;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class RestAssuredUtility {
    private String baseUrl;

    public RestAssuredUtility(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String buildUrl(String endpoint) {
        return baseUrl + endpoint;
    }

    public Response sendRequest(String method, String endpoint, Map<String, String> headers, String body) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUrl)
                .setBasePath(endpoint)
                .addHeaders(headers);

        if (!headers.isEmpty()) {
            requestSpecBuilder.addHeaders(headers);
        }

        if (body != null) {
            requestSpecBuilder.setBody(body);
        }

        Response response;
        switch (method) {
            case "GET":
                response = RestAssured.given(requestSpecBuilder.build()).get();
                break;
            case "POST":
                response = RestAssured.given(requestSpecBuilder.build()).post();
                break;
            case "PUT":
                response = RestAssured.given(requestSpecBuilder.build()).put();
                break;
            case "DELETE":
                response = RestAssured.given(requestSpecBuilder.build()).delete();
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        return response;
    }

}
