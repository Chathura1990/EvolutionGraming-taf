package com.app.evolutionGaming.Ui.navigationHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import com.app.evolutionGaming.framework.parameters.Parameters;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

@Log4j
public class NavigationService extends SelectorService {

    public NavigationService(WebDriver driver) {
        super(driver);
    }

    private By memoButton = xpath("//a[@title='Memo']");
    private By searchButton = xpath("//a[@title='Search announcements']");

    public void goToMainPage() {
        driver.get(Parameters.instance().getUrl() + Parameters.instance().getLanguage());
        String title = getWebsiteTitle();
        log.info("Go to " + title + " page");
    }



    public void goToSearchPage() {
        click(searchButton, "Search");
        String title = getWebsiteTitle();
        log.info("Go to " + title + " page");
    }

    public void goToMemoPage() {
        scrollIntoLocatorAndClick(memoButton, "memo button");
        String title = getWebsiteTitle();
        log.info("Go to " + title + " page");
    }
}
