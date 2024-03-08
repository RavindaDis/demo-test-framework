package app.demo.tests.api.fuota;

import app.demo.api.base.ApiBase;
import app.demo.helper.FuotaRequestHelper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FileTransferRequestHelper.*;
import static app.demo.helper.FuotaRequestHelper.sendFuotaRequest;
import static app.demo.helper.FuotaRequestHelper.verifyFuotaRequestResponse;
import static app.demo.helper.FuotaStatusHelper.*;
import static app.demo.utils.Constants.*;
import static app.demo.utils.JsonFileReader.readIntegerFromJsonFile;
import static app.demo.utils.JsonFileReader.readStringFromJsonFile;
import static app.demo.utils.JsonFileWriter.writeToJsonFile;

public class MlFuotaCompleteFlowTest extends ApiBase {

    private final String payloadFileFuota = FUOTA_ML_SUCCESS_PAYLOAD;
    private final String payloadFileFileTransfer = FT_ML_SUCCESS_PAYLOAD;
    private final String fileName = readStringFromJsonFile(payloadFileFileTransfer, FILE_NAME);
    private final String version = readStringFromJsonFile(payloadFileFileTransfer, VERSION);
    private final int fileId = readIntegerFromJsonFile(payloadFileFileTransfer, FILE_ID_KEY);
    private final String gatewayEui = readStringFromJsonFile(payloadFileFuota, GATEWAY_EUI_KEY);
    private final int mgId = readIntegerFromJsonFile(payloadFileFuota, MG_ID);
    private final String siteId = readStringFromJsonFile(payloadFileFuota, SITE_ID_KEY);
    private final int dataFrequency = readIntegerFromJsonFile(payloadFileFuota, DATA_FREQUENCY);
    private final int fragDelay = readIntegerFromJsonFile(payloadFileFuota, FRAG_DELAY);

    private final Boolean isGwInFallback = false;
    public static String fileTransferJobId;

    @BeforeMethod(groups = {API_REG, ML_FUOTA_TEST})
    public void testDataCreation() throws IOException, InterruptedException {
        Response mlFileTransferResponse = sendFileTransferRequest(payloadFileFileTransfer, GTW_PROD_HEADER, ML_FILE_TRANSFER_ENDPOINT);

        verifyFileTransferRequestResponse(mlFileTransferResponse);
        fileTransferJobId = getJobId(mlFileTransferResponse);
        writeToJsonFile(payloadFileFuota, FILE_TRANSFER_JOB_ID, fileTransferJobId);

        Thread.sleep(THREE_SECONDS + timeForDisablePsmStage(isGwInFallback) + timeForGatewayFileDownloadStage() + timeForEnablePsmStage());

        verifyFileTransferStatusCompletedResponse(getFuotaStatus(fileTransferJobId));
    }

    @Test(groups = {API_REG, ML_FUOTA_TEST})
    public void mlFuotaCompleteFlowTest() throws InterruptedException, IOException {

        Response mlFuotaResponse = sendFuotaRequest(payloadFileFuota, GTW_PROD_AND_TTI_PILOT_HEADER, ML_FUOTA_ENDPOINT);
        String jobCreatedTime = getCurrentTimeInFuotaFormat();

        verifyFuotaRequestResponse(mlFuotaResponse);
        String jobId = FuotaRequestHelper.getJobId(mlFuotaResponse);

        Thread.sleep(THREE_SECONDS);

        //Verifying initial FUOTA status response
        verifyFuotaStatusInitialResponse(getFuotaStatus(jobId), jobId, gatewayEui, mgId, fileName, version, fileId, dataFrequency, FUOTA_TYPE_ML, JOB_TYPE_FUOTA, fileTransferJobId, siteId, jobCreatedTime);

        Thread.sleep(timeForDisablePsmStage(isGwInFallback));

        //Verifying FUOTA status response for Disable PSM stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), DISABLE_PSM_STAGE, true, fileId);

        Thread.sleep(timeForChangeUlFilterStage());

        //Verifying FUOTA status response for Enable Ul Filter stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), ENABLE_UL_FILTER_STAGE, true, fileId);

        Thread.sleep(timeForStartFuotaStage(dataFrequency));

        //Verifying FUOTA status response for Start Fuota stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), START_FUOTA_STAGE, true, fileId);

        int noOfFrags = 20;
        Thread.sleep(timeForSendFragsStage(noOfFrags, fragDelay));

        //Verifying FUOTA status response for Send Frags stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), SEND_FRAGS_STAGE, true, fileId);

        Thread.sleep(timeForChangeUlFilterStage());

        //Verifying FUOTA status response for Disable Ul Filter stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), DISABLE_UL_FILTER_STAGE, true, fileId);

        Thread.sleep(timeForEnablePsmStage());

        //Verifying FUOTA status response for Enable PSM stage
        verifyFuotaStatusStageLevelResponse(getFuotaStatus(jobId), ENABLE_PSM_STAGE, true, fileId);

        Thread.sleep(THREE_SECONDS);

        //Verify FUOTA status response after the job is completed
        verifyFuotaStatusCompletedResponse(getFuotaStatus(jobId), JOB_COMPLETED);
    }
}
