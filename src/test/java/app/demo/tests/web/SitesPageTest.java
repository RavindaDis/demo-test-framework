package app.demo.tests.web;

import app.demo.utils.Constants;
import app.demo.web.base.WebBase;
import org.testng.annotations.Test;

import java.util.Date;

public class SitesPageTest extends WebBase {

    private String uniqueSiteName;

    @Test(groups = Constants.SITES_TEST, priority = 1)
    public void verifySiteCreation() throws InterruptedException {
        uniqueSiteName = createUniqueSiteName();

        //test steps
        sideBarPanel.clickMenuItemSites();

        createSite(uniqueSiteName);

        sitesPage.getSiteNameElement(uniqueSiteName).isDisplayed();
    }

    @Test(groups = Constants.SITES_TEST, priority = 2)
    public void verifySiteEditing() {
        String editedUniqueSiteName = uniqueSiteName.concat(" Edited");

        //test steps
        updateSiteName(uniqueSiteName, editedUniqueSiteName);

        sitesPage.getSiteNameElement(editedUniqueSiteName).isDisplayed();
    }

    protected void createSite(String siteName) {
        sitesPage
                .clickBtnAddSite();

        String clientName = "Cloud Automation";
        String regionName = "Europe";
        addSitePanel
                .insertSiteName(siteName)
                .selectClient(clientName)
                .selectRegion(regionName);

        addSitePanel
                .clickBtnSubmit();
    }

    private void updateSiteName(String originalSiteName, String newSiteTitle) {
        sitesPage
                .ClickBtnMore(originalSiteName);
        siteMorePanel
                .clickBtnEdit();
        editSiteInfoPanel
                .insertName(newSiteTitle)
                .clickBtnSubmit();
    }


    private String createUniqueSiteName() {
        Date d = new Date(System.currentTimeMillis());
        String siteName = "Cloud Team";
        return siteName.concat("-") + d;
    }
}
