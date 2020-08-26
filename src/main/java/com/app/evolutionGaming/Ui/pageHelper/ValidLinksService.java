package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.app.evolutionGaming.Ui.types.Timeouts.INCREASED_TIMEOUT_SECS;
import static org.openqa.selenium.By.tagName;

@Log4j
public class ValidLinksService extends SelectorService {

    public ValidLinksService(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getWebElements() {
        //1.Get the list of all the links,images,inputs and buttons.
        List<WebElement> AllTheLinkList = driver.findElements(tagName("a"));
        AllTheLinkList.addAll(driver.findElements(tagName("img")));

        log.info("Total amount of links ----> " + AllTheLinkList.size());
        List<WebElement> activeLinks = new ArrayList<>();
        //2.Iterate LinksList: Exclude all the links/images - doesn't have any href attribute and exclude images starting with javascript.
        for (WebElement link : AllTheLinkList) {
            try {
                if (link.getAttribute("href") != null && !link.getAttribute("href").contains("javascript") && !link.getAttribute("href").matches(".*/\\d+$")) {
                    activeLinks.add(link);
                }
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                ex.printStackTrace();
            }
        }
        //Get total amount of Other links
        log.info("Other Links ---> " + (AllTheLinkList.size() - activeLinks.size()));
        //Get total amount of links in the page
        log.info("Size of active links and images ---> " + activeLinks.size());
        return activeLinks;
    }

    public void checkAllValidLinks(List<WebElement> activeLinks) {
        for (int j = 0; j < activeLinks.size(); j++) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
                connection.setConnectTimeout(INCREASED_TIMEOUT_SECS);
                connection.setInstanceFollowRedirects(false);
                connection.connect();
                String response = connection.getResponseMessage(); //Ok
                int code = connection.getResponseCode();
                connection.disconnect();
                if (code == 400 | code == 403 | code == 404 | code == 500) {
                    log.error((j + 1) + "/" + activeLinks.size() + " " + "Corrupted link" + ":--->" + activeLinks.get(j).getAttribute("href") + " " + "-- Code:" + code);
                } else {
                    log.info((j + 1) + "/" + activeLinks.size() + " " + activeLinks.get(j).getAttribute("href") + " " + "---> Status:" + response + " ----> Code:" + code);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
