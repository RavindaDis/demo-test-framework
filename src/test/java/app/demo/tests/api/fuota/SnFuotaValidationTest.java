package app.demo.tests.api.fuota;

import app.demo.utils.Constants;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FuotaRequestHelper.sendFuotaRequest;
import static app.demo.helper.FuotaRequestHelper.verifyBadFuotaRequestResponse;

public class SnFuotaValidationTest extends ApiBase {

    @DataProvider
    public Object[][] dpValidationSnFuota() {
        return new Object[][]{
                // payloadFileName, headerFileName, expectedErrorMessage
                {Constants.FUOTA_NO_GATEWAY_EUI, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.EU_ID_BLANK_ERROR},
                {Constants.FUOTA_NO_FT_JOB_ID, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.FT_JOB_ID_BLANK_ERROR},
                {Constants.FUOTA_NO_SENSORS, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.SENSOR_IDS_EMPTY_ERROR},
                {Constants.FUOTA_NO_MG_ID, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.MG_ID_ERROR},
                {Constants.FUOTA_NO_SITE_ID, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.SITE_ID_BLANK_ERROR},
                {Constants.FUOTA_DATA_FREQ_BELOW_MIN, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.DATA_FREQUENCY_ERROR},
                {Constants.FUOTA_FRAG_DELAY_BELOW_MIN, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.FRAG_DELAY_ERROR},
                {Constants.FUOTA_GATEWAY_NOT_FOUND, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.GATEWAY_NOT_FOUND_ERROR},
                {Constants.FUOTA_FT_JOB_NOT_FOUND, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.FT_JOB_NOT_FOUND_ERROR},
                {Constants.FUOTA_SITE_NOT_FOUND, Constants.GTW_PROD_AND_TTI_PILOT_HEADER, Constants.SITE_NOT_FOUND_ERROR},
                {Constants.FUOTA_SN_SUCCESS_PAYLOAD, Constants.TTI_PILOT_HEADER, Constants.GTW_TTI_HEADERS_INVALID_ERROR},
                {Constants.FUOTA_SN_SUCCESS_PAYLOAD, Constants.GTW_PROD_HEADER, Constants.GTW_TTI_HEADERS_INVALID_ERROR}
        };
    }

    @Test(dataProvider = "dpValidationSnFuota", groups = {Constants.API_REG, Constants.SN_FUOTA_TEST})
    public void snFuotaValidations(String payloadFileName, String headerFileName, String expectedErrorMessage) throws IOException {

        Response snFuotaResponse = sendFuotaRequest(payloadFileName, headerFileName, Constants.SN_FUOTA_ENDPOINT + Constants.FUOTA_SERVICE_DISABLE_JOB_CHECK);
        verifyBadFuotaRequestResponse(snFuotaResponse, expectedErrorMessage);
    }
}
