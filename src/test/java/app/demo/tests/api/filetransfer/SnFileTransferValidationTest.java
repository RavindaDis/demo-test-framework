package app.demo.tests.api.filetransfer;

import app.demo.utils.Constants;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FileTransferRequestHelper.sendFileTransferRequest;
import static app.demo.helper.FileTransferRequestHelper.verifyBadFileTransferRequestResponse;

public class SnFileTransferValidationTest extends ApiBase {

    @DataProvider
    public Object[][] dataProviderSnFtValidations() {
        return new Object[][]{
                // payloadFileName, headerFileName, expectedErrorMessage
                {Constants.FT_NO_GATEWAY_EUI, Constants.GTW_PROD_HEADER, Constants.GATEWAY_EUI_BLANK_ERROR},
                {Constants.FT_NO_FILE_NAME, Constants.GTW_PROD_HEADER, Constants.FILE_NAME_ERROR},
                {Constants.FT_NO_VERSION, Constants.GTW_PROD_HEADER, Constants.VERSION_BLANK_ERROR},
                {Constants.FT_NO_FILE_ID, Constants.GTW_PROD_HEADER, Constants.FILE_ID_MIN_ERROR},
                {Constants.FT_NO_SITE_ID, Constants.GTW_PROD_HEADER, Constants.SITE_ID_BLANK_ERROR},
                {Constants.FT_GATEWAY_NOT_FOUND, Constants.GTW_PROD_HEADER, Constants.GATEWAY_NOT_FOUND_ERROR},
                {Constants.FT_FILE_NOT_FOUND, Constants.GTW_PROD_HEADER, Constants.FILE_NOT_FOUND_ERROR},
                {Constants.FT_FILE_ID_BELOW_MIN, Constants.GTW_PROD_HEADER, Constants.FILE_ID_MIN_ERROR},
                {Constants.FT_FILE_ID_ABOVE_MAX, Constants.GTW_PROD_HEADER, Constants.FILE_ID_MAX_ERROR},
                {Constants.FT_SN_SUCCESS_PAYLOAD, Constants.TTI_PILOT_HEADER, Constants.GTW_HEADER_INVALID_ERROR}
        };
    }

    @Test(dataProvider = "dataProviderSnFtValidations", groups = {Constants.API_REG, Constants.SN_FT_TEST})
    public void snFileTransferValidations(String payloadFileName, String headerFileName, String expectedErrorMessage) throws IOException {
        //Sending the File Transfer API request
        Response snFileTransferResponse = sendFileTransferRequest(payloadFileName, headerFileName, Constants.SN_FILE_TRANSFER_ENDPOINT + Constants.FUOTA_SERVICE_DISABLE_JOB_CHECK);

        //Verifying File Transfer API response
        verifyBadFileTransferRequestResponse(snFileTransferResponse, expectedErrorMessage);
    }
}
