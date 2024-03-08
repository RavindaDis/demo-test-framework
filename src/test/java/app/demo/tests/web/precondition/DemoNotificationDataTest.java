package app.demo.tests.web.precondition;

import app.demo.api.base.ApiBase;
import org.testng.annotations.BeforeSuite;

import static app.demo.helper.SimulateMsgHelper.sendSimulateMsgHex;
import static app.demo.utils.Constants.*;

public class DemoNotificationDataTest extends ApiBase {

    public static String NotificationTriggeredTime;

    @BeforeSuite(groups = {API_REG, DEMO_TEST})
    public void simulateDemoNotification() {
        //Sending the hex simulate message
        int framePort = 104;
        int endDeviceId1 = 995;
        String rawInput = "0E72";

        NotificationTriggeredTime = sendSimulateMsgHex(endDeviceId1, framePort, rawInput);
    }

}
