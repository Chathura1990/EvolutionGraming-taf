package com.app.evolutionGaming.tests;

import com.app.evolutionGaming.Ui.testBase.TestBase;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Log4j
public class CollectAllValidLinksTest extends TestBase {

    @Priority(1)
    @Test(priority = 1, description = "Get the list of all the valid links,images,inputs and buttons from main page")
    public void searchAllTheValidLinksInMainPageTest() {
        log.info("********** Started Checking valid links **********");
        app.getNavigationService().goToMainPage();
        String title = app.getSelectorService().getWebsiteTitle();
        Assert.assertEquals(title, "SS.COM - Announcements");
        List<WebElement> activeLinks = app.getValidLinksService().getWebElements();
        app.getValidLinksService().checkAllValidLinks(activeLinks);
    }
}


