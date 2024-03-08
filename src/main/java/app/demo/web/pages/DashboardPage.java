package app.demo.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    //Locators
    private final By listSensorOfEachSite = By.xpath("//h3[contains(text(),'Sensors of each site')]/parent::div/parent::div");
    private final By listGatewaysOfEachSite = By.xpath("//h3[contains(text(),'Gateways of each site')]/parent::div/parent::div");
    private final By listCoveredAreaOfEachSite = By.xpath("//h3[contains(text(),'Covered area of each site')]/parent::div/parent::div");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * get the site names displayed under 'Sensors of each site' section
     *
     * @return web element
     */
    public WebElement getElementsFromSensorsList(){
        return driver.findElement(listSensorOfEachSite);
    }

    /**
     * get the site names displayed under 'Gateways of each site' section
     *
     * @return web element
     */
    public WebElement getElementsFromGatewaysList(){
        return driver.findElement(listGatewaysOfEachSite);
    }

    /**
     * get the site names displayed under 'Covered area of each site' section
     *
     * @return web element
     */
    public WebElement getElementsFromCoveredAreaList(){
        return driver.findElement(listCoveredAreaOfEachSite);
    }

    /**
     * get the element which displays the count under a given section for a given site
     *
     * @return web element
     */
    public WebElement getCountElementForGivenSite(String siteName, WebElement scope){
        return scope.findElement(By.xpath(".//*[text()=' "+siteName+" ']/parent::h4/parent::div/h4[2]"));
    }

    /**
     * click on a given site name link under a given section
     *
     * @return SiteInfoPage
     */
    public SiteInfoPage clickOnSiteNameLink(String siteName, WebElement scope){
        scope.findElement(By.xpath(".//*[text()=' "+siteName+" ']")).click();
        return new SiteInfoPage(driver);
    }


}
