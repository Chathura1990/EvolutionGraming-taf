package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import com.app.evolutionGaming.Ui.types.Timeouts;
import lombok.extern.log4j.Log4j;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.*;

@Log4j
public class AnnouncementService extends SelectorService {

    private By announcements = xpath("//input[@type='checkbox']/../../td[3]/div/a");
    private By addToFavoriteButton = xpath("//a[@id='a_fav']");
    private By alertOk = xpath("//a[@id='alert_ok']");

    public AnnouncementService(WebDriver driver) {
        super(driver);
    }

    public WebElement getAnnouncementElementByRowNumber(int rowNumber) {
        List<WebElement> rows = driver.findElements(announcements);
        return rows.get(rowNumber);
    }

    public void selectTheAvailableAnnouncement(WebElement announcement, String announcementName) {
        clickElement(announcement, announcementName);
    }

    public void clickAddToFavoriteButton() {
        waitLocatorToBeClickable(addToFavoriteButton);
        scrollIntoLocatorAndClick(addToFavoriteButton, "[add to favorite] button");
        click(alertOk,"[alert Ok] button");
    }

    public String getAnnouncementName(WebElement element) {
        return element.getText();
    }

    public int checkAvailableAnnouncementsInThePage() {
        int count = driver.findElements(announcements).size();
        log.info("Available announcements in the page: " + count);
        return count;
    }

}
