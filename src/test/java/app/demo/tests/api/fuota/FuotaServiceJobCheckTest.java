package app.demo.tests.api.fuota;

import app.demo.utils.Constants;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FileTransferRequestHelper.*;
import static app.demo.helper.FuotaRequestHelper.sendFuotaRequest;
import static app.demo.helper.FuotaRequestHelper.verifyBadFuotaRequestResponse;
import static app.demo.helper.FuotaStatusHelper.*;
import static app.demo.utils.JsonFileWriter.writeToJsonFile;

public class FuotaServiceJobCheckTest extends ApiBase {

    public static String fileTransferJobId;

    @BeforeClass(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void testDataCreation() throws IOException, InterruptedException {
        Response mlFileTransferResponse = sendFileTransferRequest(Constants.FT_ML_SUCCESS_PAYLOAD, Constants.GTW_PROD_HEADER, Constants.ML_FILE_TRANSFER_ENDPOINT + Constants.FUOTA_SERVICE_DISABLE_JOB_CHECK);

        verifyFileTransferRequestResponse(mlFileTransferResponse);
        fileTransferJobId = getJobId(mlFileTransferResponse);
        writeToJsonFile(Constants.FT_ML_SUCCESS_PAYLOAD, Constants.FILE_TRANSFER_JOB_ID, fileTransferJobId);
        Thread.sleep(Constants.THREE_SECONDS);
    }

    @Test(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void validateJobCheckMlFileTransfer() throws IOException {

        Response mlFileTransferResponse = sendFileTransferRequest(Constants.FT_ML_SUCCESS_PAYLOAD, Constants.GTW_PROD_HEADER, Constants.ML_FILE_TRANSFER_ENDPOINT);

        verifyBadFuotaRequestResponse(mlFileTransferResponse, Constants.JOB_ALREADY_RUNNING_ERROR);
    }

    @Test(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void validateJobCheckSnFileTransfer() throws IOException {

        Response snFileTransferResponse = sendFileTransferRequest(Constants.FT_SN_SUCCESS_PAYLOAD, Constants.GTW_PROD_HEADER, Constants.SN_FILE_TRANSFER_ENDPOINT);

        verifyBadFuotaRequestResponse(snFileTransferResponse, Constants.JOB_ALREADY_RUNNING_ERROR);
    }

    @Test(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void validateJobCheckMlFuota() throws IOException {

        Response mlFuotaResponse = sendFuotaRequest(Constants.FUOTA_ML_SUCCESS_PAYLOAD, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.ML_FUOTA_ENDPOINT);

        verifyBadFuotaRequestResponse(mlFuotaResponse, Constants.JOB_ALREADY_RUNNING_ERROR);
    }

    @Test(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void validateJobCheckSnFuota() throws IOException {

        Response snFuotaResponse = sendFuotaRequest(Constants.FUOTA_SN_SUCCESS_PAYLOAD, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.SN_FUOTA_ENDPOINT);

        verifyBadFuotaRequestResponse(snFuotaResponse, Constants.JOB_ALREADY_RUNNING_ERROR);
    }

    @AfterClass(groups = {Constants.API_REG, Constants.ML_FUOTA_TEST})
    public void waitTillJobIsCompleted() throws InterruptedException {
        Thread.sleep(timeForDisablePsmStage(false) + timeForGatewayFileDownloadStage() + timeForEnablePsmStage());
    }
}
