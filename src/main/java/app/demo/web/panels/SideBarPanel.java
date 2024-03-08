package app.demo.web.panels;

import app.demo.web.pages.SitesPage;
import app.demo.web.pages.BasePage;
import org.openqa.selenium.*;

public class SideBarPanel extends BasePage {

    //Locators
    private By menuItemSites = By.xpath("//a[@title='Sites']");

    public SideBarPanel(WebDriver driver) {
        super(driver);
    }

    /**
     * click on Sites menu item
     *
     * @return Sites Page
     */
    public SitesPage clickMenuItemSites() throws InterruptedException {
        javaScriptExClick(menuItemSites);
        return new SitesPage(driver);
    }

}
