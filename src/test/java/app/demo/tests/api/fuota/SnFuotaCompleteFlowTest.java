package app.demo.tests.api.fuota;

import app.demo.helper.FuotaRequestHelper;
import app.demo.utils.Constants;
import app.demo.utils.JsonFileReader;
import app.demo.utils.JsonFileWriter;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FileTransferRequestHelper.*;
import static app.demo.helper.FuotaStatusHelper.*;

public class SnFuotaCompleteFlowTest extends ApiBase {

    private final String payloadFileFuota = Constants.FUOTA_SN_SUCCESS_PAYLOAD;
    private final String payloadFileFileTransfer = Constants.FT_SN_SUCCESS_PAYLOAD;

    private final String fileName = JsonFileReader.readStringFromJsonFile(payloadFileFileTransfer, Constants.FILE_NAME);
    private final String version = JsonFileReader.readStringFromJsonFile(payloadFileFileTransfer, Constants.VERSION);
    private final int fileId = JsonFileReader.readIntegerFromJsonFile(payloadFileFileTransfer, Constants.FILE_ID_KEY);
    private final String gatewayEui = JsonFileReader.readStringFromJsonFile(payloadFileFuota, Constants.GATEWAY_EUI_KEY);
    private final int mgId = JsonFileReader.readIntegerFromJsonFile(payloadFileFuota, Constants.MG_ID);
    private final int dataFrequency = JsonFileReader.readIntegerFromJsonFile(payloadFileFuota, Constants.DATA_FREQUENCY);
    private final int fragDelay = JsonFileReader.readIntegerFromJsonFile(payloadFileFuota, Constants.FRAG_DELAY);
    private final String siteId = JsonFileReader.readStringFromJsonFile(payloadFileFuota, Constants.SITE_ID_KEY);

    private final Boolean isGwInFallback = false;
    public static String fileTransferJobId;

    @BeforeMethod(groups = {Constants.API_REG, Constants.SN_FUOTA_TEST})
    public void testDataCreation() throws IOException, InterruptedException {
        Response snFileTransferResponse = sendFileTransferRequest(payloadFileFileTransfer, Constants.GTW_PROD_HEADER, Constants.SN_FILE_TRANSFER_ENDPOINT);

        verifyFileTransferRequestResponse(snFileTransferResponse);
        fileTransferJobId = getJobId(snFileTransferResponse);
        JsonFileWriter.writeToJsonFile(payloadFileFuota, Constants.FILE_TRANSFER_JOB_ID, fileTransferJobId);

        Thread.sleep(Constants.THREE_SECONDS + timeForDisablePsmStage(isGwInFallback) + timeForGatewayFileDownloadStage() + timeForEnablePsmStage());

        verifyFileTransferStatusCompletedResponse(getFuotaStatus(fileTransferJobId));
    }

    @Test(groups = {Constants.API_REG, Constants.SN_FUOTA_TEST})
    public void snFuotaCompleteFlowTest() throws InterruptedException, IOException {

        Response snFuotaResponse = FuotaRequestHelper.sendFuotaRequest(payloadFileFuota, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.SN_FUOTA_ENDPOINT);
        String jobCreatedTime = getCurrentTimeInFuotaFormat();

        FuotaRequestHelper.verifyFuotaRequestResponse(snFuotaResponse);
        String jobId = FuotaRequestHelper.getJobId(snFuotaResponse);

        Thread.sleep(Constants.THREE_SECONDS);

        //Verifying initial FUOTA status response
        verifyFuotaStatusInitialResponse(getFuotaStatus(jobId), jobId, gatewayEui, mgId, fileName, version, fileId, dataFrequency, Constants.FUOTA_TYPE_SN, Constants.JOB_TYPE_FUOTA, fileTransferJobId, siteId, jobCreatedTime);

        Thread.sleep(timeForDisablePsmStage(isGwInFallback));

        //Verifying FUOTA status response for Gateway Fallback stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.DISABLE_PSM_STAGE, true, fileId);

        Thread.sleep(timeForChangeUlFilterStage());

        //Verifying FUOTA status response for Enable Ul Filter stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.ENABLE_UL_FILTER_STAGE, true, fileId);

        Thread.sleep(timeForStartFuotaStage(dataFrequency));

        //Verifying FUOTA status response for Start Fuota stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.START_FUOTA_STAGE, true, fileId);

        int noOfFrags = 1530;
        Thread.sleep(timeForSendFragsStage(noOfFrags, fragDelay));

        //Verifying FUOTA status response for Send Frags stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.SEND_FRAGS_STAGE, true, fileId);

        Thread.sleep(timeForChangeUlFilterStage());

        //Verifying FUOTA status response for Disable Ul Filter stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.DISABLE_UL_FILTER_STAGE, true, fileId);

        Thread.sleep(timeForEnablePsmStage());

        //Verifying FUOTA status response for Wrap up stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), Constants.ENABLE_PSM_STAGE, true, fileId);

        Thread.sleep(Constants.THREE_SECONDS);

        //Verify FUOTA status response after the job is completed
        verifyFuotaStatusCompletedResponse(getFuotaStatus(jobId), Constants.JOB_COMPLETED);
    }
}
