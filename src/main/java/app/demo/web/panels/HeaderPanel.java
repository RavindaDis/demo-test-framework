package app.demo.web.panels;

import app.demo.web.pages.BasePage;
import org.openqa.selenium.*;

public class HeaderPanel extends BasePage {

    //Locators
    private By btnDemoNotification = By.xpath("//*[@data-cy='demo-notification']");

    public HeaderPanel(WebDriver driver) {
        super(driver);
    }

    /**
     * get text of demo notification button
     *
     * @return Alert Center Page
     */
    public String getBtnDemoNotificationText(){
        return getTextOfElement(btnDemoNotification);
    }

    /**
     * click on demo notification button
     *
     * @return Alert Center Page
     */
    public AlertCenterPage clickBtnDemoNotification() {
        javaScriptExClick(btnDemoNotification);
        return new AlertCenterPage(driver);
    }
}
