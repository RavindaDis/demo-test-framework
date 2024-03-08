package app.demo.tests.web;

import app.demo.utils.Constants;
import app.demo.web.base.WebBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SiteInformationTest extends WebBase {

    private final String siteName = "Cloud Team";

    @Test(groups = Constants.SITE_INFO_TEST, priority = 1)
    public void preconditions() throws InterruptedException {
        sideBarPanel.clickMenuItemSites();

        sitesPage
                .ClickBtnMore(siteName);
        siteMorePanel
                .clickBtnOpen();
    }

    @Test(groups = Constants.SITE_INFO_TEST, priority = 2)
    public void verifySiteName() {
        Assert.assertEquals(siteInfoPage.getTextOfSiteNameElement(), siteName, "Expected site name not found");
    }

    @Test(groups = Constants.SITE_INFO_TEST, priority = 3)
    public void verifySensorNodeCount() {
        siteInfoPage
                .clickTabSensors();

        int actualSensorNodeCount = siteInfoPage.getSensorNodeCount();
        int expectedSensorNodeCount = 10;
        Assert.assertEquals(actualSensorNodeCount, expectedSensorNodeCount, "expected number of sensor nodes not found");
    }

    @Test(groups = Constants.SITE_INFO_TEST, priority = 4)
    public void verifyGatewayCount() {
        siteInfoPage
                .clickTabGateways();

        int actualGatewayCount = siteInfoPage.getGatewayCount();
        int expectedGatewayCount = 3;
        Assert.assertEquals(actualGatewayCount, expectedGatewayCount, "expected number of gateways not found");
    }
}
