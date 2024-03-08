package app.demo.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePage{

    //Locators
    private By btnLogin = By.xpath("//span[normalize-space()='Login']");


    public WelcomePage(WebDriver driver){
        super(driver);
    }

    /**
     * click login button
     * @return login page
     */
    public LoginPage clickBtnLogin(){
        clickElement(btnLogin);
        return new LoginPage(driver);
    }

}
