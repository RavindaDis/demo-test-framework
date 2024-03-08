package app.demo.web.panels;

import app.demo.web.pages.DashboardPage;
import app.demo.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditSiteInfoPanel extends BasePage {

    private By txtName = By.xpath("//input[@data-cy='name']");
    private By btnSubmit = By.xpath("//demo-button[@label=\"Submit\"]");

    public EditSiteInfoPanel(WebDriver driver) {
        super(driver);
    }

    /**
     * enter a new name for the site
     *
     * @param newName
     * @return homepage
     */
    public EditSiteInfoPanel insertName(String newName) {
        insertText(txtName, newName);
        return this;
    }

    /**
     * click Submit button
     *
     * @return homepage
     */
    public DashboardPage clickBtnSubmit() {
        clickElement(btnSubmit);
        return new DashboardPage(driver);
    }
}
