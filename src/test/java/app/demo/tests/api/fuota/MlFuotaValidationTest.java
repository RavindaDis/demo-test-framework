package app.demo.tests.api.fuota;

import app.demo.api.base.ApiBase;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.demo.helper.FuotaRequestHelper.sendFuotaRequest;
import static app.demo.helper.FuotaRequestHelper.verifyBadFuotaRequestResponse;
import static app.demo.utils.Constants.*;

public class MlFuotaValidationTest extends ApiBase {

    @DataProvider
    public Object[][] dpMlFuotaValidations() {
        return new Object[][]{
                // payloadFileName, headerFileName, expectedErrorMessage
                {FUOTA_NO_GATEWAY_EUI, GTW_PROD_AND_TTI_PILOT_HEADER, EU_ID_BLANK_ERROR},
                {FUOTA_NO_FT_JOB_ID, GTW_PROD_AND_TTI_PILOT_HEADER, FT_JOB_ID_BLANK_ERROR},
                {FUOTA_NO_SENSORS, GTW_PROD_AND_TTI_PILOT_HEADER, SENSOR_IDS_EMPTY_ERROR},
                {FUOTA_NO_MG_ID, GTW_PROD_AND_TTI_PILOT_HEADER, MG_ID_ERROR},
                {FUOTA_NO_SITE_ID, GTW_PROD_AND_TTI_PILOT_HEADER, SITE_ID_BLANK_ERROR},
                {FUOTA_DATA_FREQ_BELOW_MIN, GTW_PROD_AND_TTI_PILOT_HEADER, DATA_FREQUENCY_ERROR},
                {FUOTA_FRAG_DELAY_BELOW_MIN, GTW_PROD_AND_TTI_PILOT_HEADER, FRAG_DELAY_ERROR},
                {FUOTA_GATEWAY_NOT_FOUND, GTW_PROD_AND_TTI_PILOT_HEADER, GATEWAY_NOT_FOUND_ERROR},
                {FUOTA_FT_JOB_NOT_FOUND, GTW_PROD_AND_TTI_PILOT_HEADER, FT_JOB_NOT_FOUND_ERROR},
                {FUOTA_SITE_NOT_FOUND, GTW_PROD_AND_TTI_PILOT_HEADER, SITE_NOT_FOUND_ERROR},
                {FUOTA_ML_SUCCESS_PAYLOAD, TTI_PILOT_HEADER, GTW_TTI_HEADERS_INVALID_ERROR},
                {FUOTA_ML_SUCCESS_PAYLOAD, GTW_PROD_HEADER, GTW_TTI_HEADERS_INVALID_ERROR}
        };
    }

    @Test(dataProvider = "dpMlFuotaValidations", groups = {API_REG, ML_FUOTA_TEST})
    public void mlFuotaValidations(String payloadFileName, String headerFileName, String expectedErrorMessage) throws IOException {

        Response mlFuotaResponse = sendFuotaRequest(payloadFileName, headerFileName, ML_FUOTA_ENDPOINT + FUOTA_SERVICE_DISABLE_JOB_CHECK);
        verifyBadFuotaRequestResponse(mlFuotaResponse, expectedErrorMessage);
    }
}
