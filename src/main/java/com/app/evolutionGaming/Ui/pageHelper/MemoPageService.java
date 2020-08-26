package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.openqa.selenium.By.xpath;

@Log4j
public class MemoPageService extends SelectorService {

    private By checkbox = xpath("//input[@type='checkbox']");

    public MemoPageService(WebDriver driver) {
        super(driver);
    }

    public void verifyAddedMemo(String announcementName){
        if (isElementPresent(checkbox)) {
            List<WebElement> checkboxList = driver.findElements(checkbox);
            int countRows = checkboxList.size();
            log.info("Available announcements in the Memo page: " + countRows);
            do {
                countRows++;
                WebElement announcement = driver.findElement(xpath("//table/tbody/tr["+ countRows +"]/td[3]/div/a"));
                String modified = announcement.getText();
                if (modified.equals(announcementName)) {
                    Assert.assertEquals(modified, announcementName);
                    log.info("Announcement added successfully");
                    break;
                }
            } while (countRows <= checkboxList.size());
        }
    }


}
