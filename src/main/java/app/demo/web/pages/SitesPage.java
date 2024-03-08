package app.demo.web.pages;

import app.demo.web.panels.SiteMorePanel;
import app.demo.web.panels.AddSitePanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SitesPage extends BasePage {

    //Locators
    private final By btnAddSite = By.xpath("//button[@data-cy='add-button']");

    public SitesPage(WebDriver driver) {
        super(driver);
    }


    /**
     * get site name displayed
     *
     * @return web element
     */
    public WebElement getSiteNameElement(String siteName){
        return getWebElementByText(siteName);
    }


    /**
     * click on more actions button for a given site
     * @param siteName - Name of the site to be selected
     * @return SiteMorePanel
     */
    public SiteMorePanel ClickBtnMore(String siteName){
        driver.findElement(By.xpath("//*[contains(text(),'"+siteName+"')]/ancestor::div[@class='p-card-content']//i")).click();
        return new SiteMorePanel(driver);
    }

    /**
     * click on add site button
     * @return add site panel
     */
    public AddSitePanel clickBtnAddSite(){
        retryingFindClick(btnAddSite);
        return new AddSitePanel(driver);
    }

}
