package app.demo.helper;

import app.demo.utils.Constants;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class FileTransferRequestHelper extends ApiBase {

    /**
     * Send Post Request to trigger a File Transfer
     *
     * @param payloadFileName
     * @param headerFileName
     * @param endPoint
     * @return response
     * @throws IOException
     */
    public static Response sendFileTransferRequest(String payloadFileName, String headerFileName, String endPoint) throws IOException {
        File fileTransferPayloadFile = new File(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_PAYLOAD_LOCATION + payloadFileName);
        File fileTransferHeaderFile = new File(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_HEADERS_LOCATION + headerFileName);

        return sendPostRequest(fileTransferPayloadFile, fileTransferHeaderFile, endPoint);
    }

    /**
     * Verify response of File Transfer request
     *
     * @param response
     */
    public static void verifyFileTransferRequestResponse(Response response) {
        response.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("jobId", notNullValue())
                .body("requestStatus", equalTo(Constants.JOB_SUBMITTED));
    }

    /**
     * Verify response of File Transfer request when the request is bad
     *
     * @param response
     */
    public static void verifyBadFileTransferRequestResponse(Response response, String expectedMessage) {
        response.then()
                .log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("errorCode", equalTo(SC_BAD_REQUEST))
                .body("message", equalToIgnoringCase(expectedMessage));
    }


    /**
     * Get the job id from File Transfer Api response
     *
     * @param response
     * @return jobId
     */
    public static String getJobId(Response response) {
        return response.then().extract().jsonPath().getString("jobId");
    }

}
