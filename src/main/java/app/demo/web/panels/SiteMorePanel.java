package app.demo.web.panels;

import app.demo.web.pages.SiteInfoPage;
import app.demo.web.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SiteMorePanel extends BasePage {

    private final By btnOpen = By.xpath("//span[contains(text(),'Open')]");
    private final By btnEdit = By.xpath("//span[contains(text(),'Edit')]");
    private final By btnPackets = By.xpath("//span[contains(text(),'Packets')]");
    private final By btnAddPackets = By.xpath("//span[contains(text(),'Add packets')]");

    public SiteMorePanel(WebDriver driver) {
        super(driver);
    }

    /**
     * click Open button
     *
     * @return SiteInfoPage
     */
    public SiteInfoPage clickBtnOpen() {
        clickElement(btnOpen);
        return new SiteInfoPage(driver);
    }

    /**
     * click Edit button
     *
     * @return SiteInfoPage
     */
    public EditSiteInfoPanel clickBtnEdit() {
        clickElement(btnEdit);
        return new EditSiteInfoPanel(driver);
    }

    /**
     * click Packets button
     *
     * @return PacketsPage
     */
    public PacketsPage clickBtnPackets() {
        clickElement(btnPackets);
        return new PacketsPage(driver);
    }

    /**
     * click Add Packet button
     *
     * @return PlanningPage
     */
    public PlanningPage clickBtnAddPacket() {
        clickElement(btnAddPackets);
        return new PlanningPage(driver);
    }
}
