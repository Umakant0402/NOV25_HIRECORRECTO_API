package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import utility.PropertyUtil;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIControlActions {
    protected static String token;//driver
    private static RequestSpecBuilder requestSpecBuilder;
    private PropertyUtil propertyUtil = new PropertyUtil("src/main/resources/config/envConfig.properties");

    private void buildRequestSpecBuilder() {
        if (requestSpecBuilder == null) {
            requestSpecBuilder = new RequestSpecBuilder();
            requestSpecBuilder.addFilter(new AllureRestAssured());
        }
        requestSpecBuilder.log(LogDetail.ALL);
    }

    protected void resetRequestBuilder() {
        requestSpecBuilder = null;
    }

    protected void setToken(String tokenValue) {
        token = tokenValue;
    }

    protected void setHeader(String key, String value) {
        buildRequestSpecBuilder();
        requestSpecBuilder.addHeader(key, value);
    }

    protected void setFormData(String key, String value) {
        buildRequestSpecBuilder();
        requestSpecBuilder.addFormParam(key, value);
    }

    protected void setHeaders(Map<String, String> headers) {
        buildRequestSpecBuilder();
        requestSpecBuilder.addHeaders(headers);
    }

    public void setBody(String body) {
        buildRequestSpecBuilder();
        requestSpecBuilder.setBody(body);
    }

    public void setBody(byte[] body) {
        buildRequestSpecBuilder();
        requestSpecBuilder.setBody(body);
    }

    public void setBaseUri(String baseUri) {
        buildRequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseUri);
    }

    public void setQueryParam(String key, String value) {
        buildRequestSpecBuilder();
        requestSpecBuilder.addQueryParam(key, value);
    }

    public void setQueryParams(Map<String, String> queryParams) {
        buildRequestSpecBuilder();
        requestSpecBuilder.addQueryParams(queryParams);
    }

    public Response executePatchAPI(String endPoint) {
        String baseURI = propertyUtil.getProperty(ApplicationConfig.getEnvironment());

        Response response = given()
                .spec(requestSpecBuilder.build())
                .baseUri(baseURI)
                .when()
                .patch(endPoint)
                .then()
                .extract()
                .response();
        requestSpecBuilder = null; // Reset the builder after request execution
        return response;
    }

    public Response executePostAPI(String endPoint) {
        String baseURI = propertyUtil.getProperty(ApplicationConfig.getEnvironment());

        Response response = given()
                .spec(requestSpecBuilder.build())
                .baseUri(baseURI)
                .when()
                .post(endPoint)
                .then()
                .extract()
                .response();
        requestSpecBuilder = null; // Reset the builder after request execution
        return response;
    }

    public Response executePutAPI(String endPoint) {
        String baseURI = propertyUtil.getProperty(ApplicationConfig.getEnvironment());

        Response response = given()
                .spec(requestSpecBuilder.build())
                .baseUri(baseURI)
                .when()
                .put(endPoint)
                .then()
                .extract()
                .response();
        requestSpecBuilder = null; // Reset the builder after request execution
        return response;
    }

    public Response executeGetAPI(String endPoint) {
        String baseURI = propertyUtil.getProperty(ApplicationConfig.getEnvironment());

        Response response = given()
                .spec(requestSpecBuilder.build())
                .baseUri(baseURI)
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();
        requestSpecBuilder = null; // Reset the builder after request execution
        return response;
    }

    public Response executeMultipartPutAPIWithFullUrl(String fullUrl) {
        Response response = given()
                .spec(requestSpecBuilder.build())
                .urlEncodingEnabled(false)
                .relaxedHTTPSValidation()
                .when()
                .put(fullUrl)
                .then()
                .extract()
                .response();
        requestSpecBuilder = null;
        return response;
    }
}
