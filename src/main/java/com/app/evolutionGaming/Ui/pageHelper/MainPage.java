package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

@Log4j
public class MainPage extends SelectorService {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By vacanciesButton = xpath("//a[@id='mtd_14080']");
    private By carsButton = xpath("//a[@id='mtd_97']");

    public void clickVacanciesPage() {
        click(vacanciesButton,"Vacancies");
        String title = getWebsiteTitle();
        log.info("Go to " + title + " page");
    }

    public void clickCarsPage(){
        click(carsButton, "Cars");
    }
}
