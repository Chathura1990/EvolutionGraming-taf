package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

@Log4j
public class VacanciesPageService extends SelectorService {

    private By Actor = xpath("//a[@id='ahc_75278']");

    public VacanciesPageService(WebDriver driver) {
        super(driver);
    }

    public void selectActorVacancy() {
        click(Actor, "Actor");
        String title = getWebsiteTitle();
        log.info("Go to " + title + " page");
    }


}
