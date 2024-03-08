package app.demo.tests.web;

import app.demo.utils.Constants;
import app.demo.web.base.WebBase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTest extends WebBase {

    private final String siteName = "Cloud Team";

    @Test(groups = Constants.DASHBOARD_TEST, priority = 1)
    public void verifySiteNameInSensorsSection() {
        assertTrue(dashboardPage.getElementsFromSensorsList().getText().contains(siteName), "Expected site name not available on the Dashboard's Sensor Nodes section");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 2)
    public void verifyCountFoSiteInSensorsSection() {
        int expectedSensorNodeCount = 10;
        assertEquals(dashboardPage.getCountElementForGivenSite(siteName, dashboardPage.getElementsFromSensorsList()).getText(), String.valueOf(expectedSensorNodeCount), "Expected sensor node count not shown for the site on the Dashboard");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 3)
    public void verifySiteNameInGatewaysSection() {
        assertTrue(dashboardPage.getElementsFromGatewaysList().getText().contains(siteName), "Expected site name not available on the Dashboard's Gateways section");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 4)
    public void verifyCountForSiteInGatewaysSection() {
        int expectedGatewayCount = 3;
        assertEquals(dashboardPage.getCountElementForGivenSite(siteName, dashboardPage.getElementsFromGatewaysList()).getText(), String.valueOf(expectedGatewayCount), "Expected gateway count not shown for the site on the Dashboard");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 5)
    public void verifySiteNameInCoverageSection() {
        assertTrue(dashboardPage.getElementsFromCoveredAreaList().getText().contains(siteName), "Expected site name not available on the Dashboard's Coverage Area Section");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 6)
    public void verifyCoverageForSiteInCoverageSection() {
        String expectedCoverageArea = "2.59 km²";
        assertEquals(dashboardPage.getCountElementForGivenSite(siteName, dashboardPage.getElementsFromCoveredAreaList()).getText(), expectedCoverageArea, "Expected coverage area not shown for the site on the Dashboard");
    }

    @Test(groups = Constants.DASHBOARD_TEST, priority = 7)
    public void verifySiteNavigation() {
        dashboardPage.clickOnSiteNameLink(siteName, dashboardPage.getElementsFromSensorsList());

        assertEquals(siteInfoPage.getTextOfSiteNameElement(), siteName, "Expected site name not found");
    }

}
