package com.app.evolutionGaming.Ui.pageHelper;

import com.app.evolutionGaming.Ui.model.SearchCarModel;
import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class SearchPageService extends SelectorService {

    public SearchPageService(WebDriver driver) {
        super(driver);
    }

    private By oughtWordOrPhrase = id("ptxt");
    private By mark = xpath("//select[@name='opt[31][]']");
    private By yearMin = xpath("//select[@name='topt[18][min]']");
    private By yearMax = xpath("//select[@name='topt[18][max]']");
    private By engineVolumeMin = xpath("//input[@name='topt[15][min]']");
    private By engineVolumeMax = xpath("//input[@name='topt[15][max]']");
    private By typeOfEngine = xpath("//select[@name='opt[34][]']");
    private By bodyType = xpath("//select[@name='opt[32][]']");
    private By vinCode = xpath("//input[@name='topt[1678]']");
    private By townRegion = id("s_region_select");
    private By searchButton = xpath("//input[@type='submit']");

    public void searchForACarWithBasicDetails(SearchCarModel searchCar){
        type(oughtWordOrPhrase, searchCar.getOughtWordOrPhrase());
        click(xpath("//div[@id='cmp_1']//b[contains(text(),'"+searchCar.getOughtWordOrPhrase()+"')]")
                ,searchCar.getOughtWordOrPhrase()+" from dropdown");
        selectAnOptionFromDropdown(mark,searchCar.getMark());
        selectAnOptionFromDropdown(yearMin, String.valueOf(searchCar.getYearMin()));
        selectAnOptionFromDropdown(yearMax, String.valueOf(searchCar.getYearMax()));
        type(engineVolumeMin, String.valueOf(searchCar.getEngineVolumeMin()));
        type(engineVolumeMax, String.valueOf(searchCar.getEngineVolumeMax()));
        selectAnOptionFromDropdown(typeOfEngine, searchCar.getEngineType());
        selectAnOptionFromDropdown(bodyType, searchCar.getBodyType());
        type(vinCode, String.valueOf(searchCar.getVinCode()));
        selectAnOptionFromDropdown(townRegion, searchCar.getTownRegion());
    }

    public void clickSearchButton(){
        click(searchButton, "Search button");
    }
}
