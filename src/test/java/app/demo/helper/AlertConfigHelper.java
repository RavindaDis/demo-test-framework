package app.demo.helper;

import app.demo.utils.Constants;
import app.demo.api.base.ApiBase;
import io.restassured.response.Response;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;

public class AlertConfigHelper extends ApiBase {
    private static Response response;

    /**
     * Update the alert configuration of the site
     * @param payloadFileName - Payload File Name as a String
     * @param siteId - ID of the site
     */
    public static void updateAlertConfig(String payloadFileName, int siteId){
        File fuotaPayloadFile = new File(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_ALERT_PAYLOAD_LOCATION + payloadFileName);

        response = sendPutRequest(fuotaPayloadFile, Constants.SITES_ENDPOINT.concat(String.valueOf(siteId)));
        response.then().log().body().assertThat().statusCode(SC_OK);
    }
}
