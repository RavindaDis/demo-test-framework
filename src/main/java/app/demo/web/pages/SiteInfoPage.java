package app.demo.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiteInfoPage extends BasePage {

    //Locators
    private final By lblSiteName = By.xpath("//h2");
    private final By tabSensors = By.xpath("//span[contains(string(),'Sensors')]");
    private final By sensorNodeContainer = By.xpath("//app-sensor-node-list");
    private final By sensorNodeList = By.xpath("//app-sensor-node-list/div");
    private final By tabGateways = By.xpath("//span[contains(string(),'Gateway')]");
    private final By gatewayContainer = By.xpath("//app-gateway");
    private final By gatewayList = By.xpath("//app-gateway/div");

    public SiteInfoPage(WebDriver driver) {
        super(driver);
    }

    /**
     * get text of site name element
     *
     * @return text of site name element
     */
    public String getTextOfSiteNameElement() {
        return getTextOfElement(lblSiteName);
    }

    /**
     * click sensors tab
     *
     * @return SiteInfoPage
     */
    public SiteInfoPage clickTabSensors() {
        clickElement(tabSensors);
        return this;
    }

    /**
     * get the number of sensor nodes available
     *
     * @return sensor node count
     */
    public int getSensorNodeCount() {
        return getItemCountOfList(sensorNodeContainer, sensorNodeList);
    }

    /**
     * get sensor node elements to a list
     *
     * @return sensor node list
     */
    public List<WebElement> getSensorNodeList() {
        return getItemList(sensorNodeContainer, sensorNodeList);
    }


    /**
     * click gateways tab
     *
     * @return SiteInfoPage
     */
    public SiteInfoPage clickTabGateways() {
        clickElement(tabGateways);
        return this;
    }

    /**
     * get the number of gateways available
     *
     * @return gateway count
     */
    public int getGatewayCount() {
        return getItemCountOfList(gatewayContainer, gatewayList);
    }
}
