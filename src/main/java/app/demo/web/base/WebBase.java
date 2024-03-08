package app.demo.web.base;

import app.demo.utils.Constants;
import app.demo.web.pages.*;
import app.demo.web.panels.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Objects;

/**
 * Base UI Class - This needs to be extended for UI testing related classes
 **/
public class WebBase {

    private static final Logger log = LoggerFactory.getLogger(WebBase.class);
    WebDriver driver;
    protected WelcomePage welcomePage;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected SitesPage sitesPage;
    protected SiteInfoPage siteInfoPage;
    protected AddSitePanel addSitePanel;
    protected EditSiteInfoPanel editSiteInfoPanel;
    protected SiteMorePanel siteMorePanel;
    protected SideBarPanel sideBarPanel;
    protected HeaderPanel headerPanel;

    /**
     * Select the browser type provided in config.properties file
     * Launch the browser and navigate to the target website
     * Execute code before each test
     */
    @BeforeClass(description = "Setup web browser and navigate to base page", groups = {Constants.ALWAYS_RUN})
    public void setupBrowser() {
        String executableBrowser = System.getenv(Constants.BROWSER);
        Boolean headLessSwitch = Boolean.valueOf(System.getenv(Constants.HEADLESS));

        if (headLessSwitch) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("window-size=1400,800");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver(options);
        } else if (Objects.equals(executableBrowser, Constants.EDGE)) {
            driver = new EdgeDriver();
        } else if (Objects.equals(executableBrowser, Constants.CHROME)) {
            driver = new ChromeDriver();
        } else if (Objects.equals(executableBrowser, Constants.FIREFOX)) {
            driver = new FirefoxDriver();
        } else {
            throw new SkipException(executableBrowser.concat(" Is an unsupported browser type"));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        instantiatePages();

        // launch Browser and direct it to the Base URL
        driver.get(System.getenv(Constants.BASE_URI));
        log.info("Launching the Website");

        // login to the application
        logIn();
    }

    /**
     * Close the browser
     * Execute code after each test
     */
    @AfterClass(description = "Close browser and quite webDriver", groups = {Constants.ALWAYS_RUN})
    public void closeBrowser() {
        if (driver != null) {
            driver.close();
            driver.quit();
            log.info("closing browser");
        }
    }

    /**
     * Create new instances of the page classes
     */
    public void instantiatePages() {
        loginPage = new LoginPage(driver);
        welcomePage = new WelcomePage(driver);
        sitesPage = new SitesPage(driver);
        dashboardPage = new DashboardPage(driver);
        siteInfoPage = new SiteInfoPage(driver);
        addSitePanel = new AddSitePanel(driver);
        editSiteInfoPanel = new EditSiteInfoPanel(driver);
        sideBarPanel = new SideBarPanel(driver);
        siteMorePanel = new SiteMorePanel(driver);
        headerPanel = new HeaderPanel(driver);
    }

    /**
     * Log in to the application
     */
    private void logIn() {
        welcomePage
                .clickBtnLogin();
        loginPage
                .enterUserName(System.getenv(Constants.USERNAME))
                .enterPassword(System.getenv(Constants.PASSWORD))
                .clickLogIn();
    }
}
