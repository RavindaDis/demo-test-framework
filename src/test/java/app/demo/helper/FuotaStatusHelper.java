package app.demo.helper;

import app.demo.api.base.ApiBase;
import app.demo.utils.Constants;
import io.restassured.response.Response;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class FuotaStatusHelper extends ApiBase {

    /**
     * Get FUOTA Status for a given job ID
     *
     * @param jobId - ID of the job which status needs to be retrieved
     * @return response
     */
    public static Response getFuotaStatus(String jobId) {
        String param = jobId.concat("?isFuota=true");
        return sendGetRequestPathParam(Constants.FUOTA_STATUS_ENDPOINT, param);
    }

    /**
     * Get current time in the format which matches with FUOTA responses
     *
     * @return time
     */
    public static String getCurrentTimeInFuotaFormat() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    /**
     * Calculate time taken for Disabling Fallback on Gateway stage with a buffer
     *
     * @return time
     */
    public static int timeForDisablePsmStage(Boolean isGatewayInFallback) {
        int sleepTime;

        if (isGatewayInFallback) {
            sleepTime = 62 * Constants.ONE_MINUTE;
        } else {
            sleepTime = 2 * Constants.ONE_MINUTE;
        }
        return sleepTime + Constants.THREE_SECONDS;
    }

    /**
     * Calculate time taken for Enabling Ul Filters on Gateways stage with a buffer
     *
     * @return time
     */
    public static int timeForChangeUlFilterStage() {
        int sleepTime = 62 * Constants.ONE_MINUTE;

        return sleepTime + Constants.THREE_SECONDS;
    }

    /**
     * Calculate time taken for Gateway File Download stage with a buffer
     *
     * @return time
     */
    public static int timeForGatewayFileDownloadStage() {
        return (2 * Constants.ONE_MINUTE) + Constants.THREE_SECONDS;
    }

    /**
     * Calculate time taken for Fragment Setup stage with a buffer
     *
     * @return time
     */
    public static int timeForStartFuotaStage(int dataFrequency) {
        return (((dataFrequency * 2) + 2) * Constants.ONE_MINUTE) + Constants.THREE_SECONDS;
    }

    /**
     * Calculate time taken for Fragment Send stage with a buffer
     *
     * @return time
     */
    public static int timeForSendFragsStage(int noOfFrags, int fragDelay) {
        int timeForFragDelay;
        if (fragDelay > 20000)
            timeForFragDelay = ((noOfFrags * fragDelay) + ((Math.min(noOfFrags, 150)) * fragDelay)) + (5 * Constants.ONE_MINUTE) + Constants.THREE_SECONDS;
        else
            timeForFragDelay = ((noOfFrags * fragDelay) + ((Math.min(noOfFrags, 150)) * 20000)) + (5 * Constants.ONE_MINUTE) + Constants.THREE_SECONDS;
        return timeForFragDelay;
    }

    /**
     * Calculate time taken for Enabling PSM stage with a buffer
     *
     * @return time
     */
    public static int timeForEnablePsmStage() {
        return Constants.ONE_MINUTE + Constants.THREE_SECONDS;
    }

    /**
     * Verify the response of FUOTA STATUS API
     *
     * @param expectedResponse - Response object of FUOTA Status API
     * @param expectedJobId - Job ID of the FUOTA
     * @param gatewayEui - EUI of the gateway used for the FUOTA
     * @param mgId - Gwid of the gateway used for the FUOTA
     * @param fileName - Name of the file used for the FUOTA
     * @param version - Version of the FUOTA file
     * @param fileId - ID of the FUOTA file in Gateway
     * @param uplinkRate - Sensor uplink interval
     * @param createdTime - Time of creation
     */
    public static void verifyFuotaStatusInitialResponse(Response expectedResponse, String expectedJobId, String gatewayEui, int mgId, String fileName, String version, int fileId, int uplinkRate, String fuotaType, String jobType, String fileTransferJobId, String siteId, String createdTime) {
        expectedResponse.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("id", equalTo(expectedJobId))
                .body("gatewayEui", equalToCompressingWhiteSpace(gatewayEui))
                .body("mg", equalTo(mgId))
                .body("fileName", equalToCompressingWhiteSpace(fileName))
                .body("fileId", equalTo(fileId))
                .body("version", equalTo(version))
                .body("uplinkRate", equalTo(uplinkRate))
                .body("jobStatus", equalTo(Constants.JOB_IN_PROGRESS))
                .body("type", equalTo(fuotaType))
                .body("jobType", equalTo(jobType))
                .body("fileTransferJobId", equalTo(fileTransferJobId))
                .body("siteId", equalTo(siteId))
                .body("createdAt", containsString(createdTime));
    }

    /**
     * Verify output related to different stages in FUOTA Status API response
     *
     * @param expectedResponse - Response object of FUOTA Status API
     * @param expectedStage - Expected name of the Stage
     * @param isStagePassed - Expected status of the Stage
     * @param fileId - Expected File ID
     */
    public static void verifyFuotaStatusStageLevelResponse(Response expectedResponse, String expectedStage, Boolean isStagePassed, int fileId) {
        expectedResponse.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("jobStageTracker.stage", hasItem(expectedStage))
                .body("jobStageTracker.status", Every.everyItem((Is.is(isStagePassed))))
                .body("jobStageTracker.fragId", hasItem(fileId));
    }

    /**
     * Verify output after completion of the job in FUOTA Status API response
     *
     * @param expectedResponse
     * @param expectedJobStatus
     */
    public static void verifyFuotaStatusCompletedResponse(Response expectedResponse, String expectedJobStatus) {
        expectedResponse.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("jobStatus", equalTo(expectedJobStatus));
    }


    /**
     * Verify the response of FUOTA STATUS API for File Transfer
     *
     * @param expectedResponse
     * @param expectedJobId
     * @param gatewayEui
     * @param fileName
     * @param fileId
     * @param jobType
     * @param createdTime
     */
    public static void verifyFileTransferStatusInitialResponse(Response expectedResponse, String expectedJobId, String gatewayEui, String fileName, int fileId, String version, String siteId, String fuotaType, String jobType, String createdTime) {
        expectedResponse.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("id", equalTo(expectedJobId))
                .body("gatewayEui", equalToCompressingWhiteSpace(gatewayEui))
                .body("fileName", equalToCompressingWhiteSpace(fileName))
                .body("fileId", equalTo(fileId))
                .body("version", equalTo(version))
                .body("siteId", equalTo(siteId))
                .body("jobStatus", equalTo(Constants.JOB_IN_PROGRESS))
                .body("type", equalTo(fuotaType))
                .body("jobType", equalTo(jobType))
                .body("createdAt", containsString(createdTime));
    }

    /**
     * Verify output after completion of the job in FUOTA Status API response for File Transfer
     *
     * @param expectedResponse
     */
    public static void verifyFileTransferStatusCompletedResponse(Response expectedResponse) {
        expectedResponse.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .body("jobStatus", equalTo(Constants.JOB_COMPLETED));
    }
}
