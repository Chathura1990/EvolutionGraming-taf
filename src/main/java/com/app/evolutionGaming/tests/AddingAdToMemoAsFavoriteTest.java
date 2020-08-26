package com.app.evolutionGaming.tests;

import com.app.evolutionGaming.Ui.model.SearchCarModel;
import com.app.evolutionGaming.Ui.testBase.TestBase;
import com.app.evolutionGaming.Ui.types.EngineTypes;
import com.app.evolutionGaming.Ui.types.TownsAndRegions;
import com.app.evolutionGaming.Ui.types.VehicleBodyTypes;
import com.app.evolutionGaming.Ui.types.VehicleMarks;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.fail;

@Log4j
public class AddingAdToMemoAsFavoriteTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> carDetails(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new SearchCarModel()
                .setOughtWordOrPhrase(VehicleMarks.MERCEDES)
                .setMark(VehicleMarks.MERCEDES)
                .setYearMin(2004)
                .setYearMax(2015)
                .setEngineVolumeMin(2)
                .setEngineVolumeMax(3.5)
                .setEngineType(EngineTypes.DIESEL)
                .setBodyType(VehicleBodyTypes.SEDAN)
                .setVinCode("WBD2200251A444468")
                .setTownRegion(TownsAndRegions.RIGA)});
        return list.iterator();
    }
//download chrome driver
    //how to run tests
    @Priority(1)
    @Test(priority = 1)
    public void AddFirstThreeAdsToMemoAsFavoriteTest() {
        log.info("***** Adding ads to memo as a favorite *****");
        goToVacanciesPageAndSelectActorVacancy();
        int announcementCount = app.getAnnouncementService().checkAvailableAnnouncementsInThePage();
        if (announcementCount > 2) {
            for (int rowNumber = 0; rowNumber < announcementCount; rowNumber++) {
                String announcementName = selectAvailableAnnouncementUsingElement(rowNumber);
                app.getAnnouncementService().clickAddToFavoriteButton();
                app.getNavigationService().goToMemoPage();
                app.getMemoPageService().verifyAddedMemo(announcementName);
                if(rowNumber == 2){
                    break;
                }
                goToVacanciesPageAndSelectActorVacancy();
            }
        }else fail("There are less than 3 announcements at the moment");
    }

    @Priority(2)
    @Test(priority = 2, dataProvider = "carDetails")
    public void AddAnAdToMemoAsFavoriteUsingAdvanceSearchTest(SearchCarModel searchCar) {
        log.info("***** Adding ads to memo as a favorite using search *****");
        app.getNavigationService().goToMainPage();
        app.getMainPage().clickCarsPage();
        app.getNavigationService().goToSearchPage();
        app.getSearchPageService().searchForACarWithBasicDetails(searchCar);
        app.getSearchPageService().clickSearchButton();
        int announcementCount = app.getAnnouncementService().checkAvailableAnnouncementsInThePage();
        if(announcementCount > 0) {
            for (int rowNumber = 0; announcementCount > rowNumber; rowNumber++) {
                String announcementName = selectAvailableAnnouncementUsingElement(rowNumber);
                app.getAnnouncementService().clickAddToFavoriteButton();
                app.getNavigationService().goToMemoPage();
                app.getMemoPageService().verifyAddedMemo(announcementName);
            }
        }else
        fail("Unable to find an announcement according to the search details");
    }

    private void goToVacanciesPageAndSelectActorVacancy() {
        app.getNavigationService().goToMainPage();
        app.getMainPage().clickVacanciesPage();
        app.getVacanciesPageService().selectActorVacancy();
    }

    private String selectAvailableAnnouncementUsingElement(int rowNumber) {
        WebElement announcement = app.getAnnouncementService().getAnnouncementElementByRowNumber(rowNumber);
        String announcementName = app.getAnnouncementService().getAnnouncementName(announcement);
        app.getAnnouncementService().selectTheAvailableAnnouncement(announcement, announcementName);
        return announcementName;
    }
}
