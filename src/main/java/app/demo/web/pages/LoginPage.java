package app.demo.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    //Locators
    private final By txtUsername = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogIn = By.id("kc-login");


    public LoginPage (WebDriver driver){
        super(driver);
    }

    /**
     * enter username
     * @param userName - User's email address
     * @return this
     */
    public LoginPage enterUserName(String userName){
        insertText(txtUsername, userName);
        return this;
    }

    /**
     * enter password
     * @param password - User's password
     * @return this
     */
    public LoginPage enterPassword(String password){
        insertText(txtPassword, password);
        return this;
    }

    /**
     * click log in button
     * @return DashboardPage
     */
    public DashboardPage clickLogIn(){
        clickElement(btnLogIn);
        return new DashboardPage(driver);
    }


}
