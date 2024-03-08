package app.demo.api.base;

import app.demo.utils.ConfigProperties;
import app.demo.utils.Constants;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;


/**
 * Base API Class - This needs to be extended for API testing related classes
 **/
public abstract class ApiBase {

    private static final Logger log = LoggerFactory.getLogger(ApiBase.class);

    /**
     * Create request specification
     *
     * @return built request specification
     */
    public static RequestSpecification createRequestSpec() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(System.getenv(Constants.BASE_URI));
        requestBuilder.addHeader(Constants.AUTH, Constants.BEARER.concat(System.getenv(Constants.AUTH_TOKEN)));
        requestBuilder.addHeader(Constants.CONTENT_TYPE, ConfigProperties.getConfigs().get(Constants.CONTENT_TYPE));
        requestBuilder.addHeader(Constants.X_REQUESTED_WITH, ConfigProperties.getConfigs().get(Constants.X_REQUESTED_WITH));
        requestBuilder.addHeader(Constants.CSRF_TOKEN, ConfigProperties.getConfigs().get(Constants.CSRF_TOKEN));
        log.info("Creating request specification");
        return requestBuilder.build();
    }


    /**
     * Send a basic get request
     *
     * @param endpoint
     * @return response
     */
    public static Response sendGetRequest(String endpoint) {
        return given()
                .spec(createRequestSpec())
                .when()
                .get(endpoint);
    }

    /**
     * Send a get request with a path parameter
     *
     * @param endpoint
     * @param pathParam
     * @return response
     */
    public static Response sendGetRequestPathParam(String endpoint, String pathParam) {
        return given()
                .spec(createRequestSpec())
                .when()
                .get(String.join("/", endpoint, pathParam));
    }

    /**
     * Send a post request
     *
     * @param endpoint
     * @return response
     */
    public static Response sendPostRequest(String endpoint) {
        return given()
                .spec(createRequestSpec())
                .when()
                .post(endpoint);
    }

    /**
     * Send a post request
     *
     * @param payload
     * @param endpoint
     * @return response
     */
    public static Response sendPostRequest(String payload, String endpoint) {
        return given()
                .spec(createRequestSpec())
                .body(payload)
                .when()
                .post(endpoint);
    }

    /**
     * Send a post request by providing the payload and headers as files
     *
     * @param payloadFileName
     * @param headerFileName
     * @param endpoint
     * @return response
     */
    public static Response sendPostRequest(File payloadFileName, File headerFileName, String endpoint) throws IOException {

        Gson gson = new Gson();
        Map headersMap = gson.fromJson(new FileReader(headerFileName), Map.class);

        return given()
                .spec(createRequestSpec())
                .headers(headersMap)
                .body(payloadFileName)
                .when()
                .post(endpoint);
    }

    /**
     * Send a put request
     *
     * @param payload
     * @param endpoint
     * @return response
     */
    public static Response sendPutRequest(String payload, String endpoint) {
        return given()
                .spec(createRequestSpec())
                .body(payload)
                .when()
                .put(endpoint);
    }

    /**
     * Send a put request providing the payload as a file
     *
     * @param payloadFileName
     * @param endpoint
     * @return response
     */
    public static Response sendPutRequest(File payloadFileName, String endpoint) {
        return given()
                .spec(createRequestSpec())
                .body(payloadFileName)
                .when()
                .put(endpoint);
    }
}
