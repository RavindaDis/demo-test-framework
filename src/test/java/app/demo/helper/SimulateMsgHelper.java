package app.demo.helper;

import app.demo.api.base.ApiBase;
import app.demo.utils.Constants;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;

public class SimulateMsgHelper extends ApiBase {
    private static Response response;

    public static String sendSimulateMsgHex(int sensorId, int framePort, String rawPayload){
        //Sending the hex simulation
        String endpoint = Constants.SIMULATE_HEX_ENDPOINT.concat(sensorId+"/"+framePort+"/"+rawPayload);
        response = sendPostRequest(endpoint);
        response.then()
                .log().all()
                .assertThat()
                .statusCode(SC_OK);
        return response.then().extract().jsonPath().getString("time");
    }
}
