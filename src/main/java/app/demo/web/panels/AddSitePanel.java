package app.demo.web.panels;

import app.demo.web.pages.SitesPage;
import app.demo.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddSitePanel extends BasePage {

    private final By txtName = By.xpath("//input[@data-cy='name']");
    private final By ddlClient = By.xpath("//p-dropdown[@data-cy='client_id']");
    private final By ddlRegion = By.xpath("//p-dropdown[@data-cy='region']");
    private final By btnSubmit = By.xpath("//dryad-button[@label='Submit']");

    public AddSitePanel(WebDriver driver) {
        super(driver);
    }

    /**
     * enter a new name for the site
     *
     * @param newSiteName - new name for the site
     * @return homepage
     */
    public AddSitePanel insertSiteName(String newSiteName) {
        insertText(txtName, newSiteName);
        return this;
    }

    /**
     * select a client for the site
     *
     * @param clientName - Name of the client
     * @return homepage
     */
    public AddSitePanel selectClient(String clientName) {
        clickElement(ddlClient);
        retryingFindClick(By.xpath("//p-dropdownitem/li[@aria-label='" + clientName + "']"));
        return this;
    }

    /**
     * select a region for the site
     *
     * @param regionName - Name of the region
     * @return homepage
     */
    public AddSitePanel selectRegion(String regionName) {
        clickElement(ddlRegion);
        retryingFindClick(By.xpath("//p-dropdownitem/li[@aria-label='" + regionName + "']"));
        return this;
    }

    /**
     * click Submit button
     *
     * @return homepage
     */
    public SitesPage clickBtnSubmit() {
        clickElement(btnSubmit);
        return new SitesPage(driver);
    }
}
