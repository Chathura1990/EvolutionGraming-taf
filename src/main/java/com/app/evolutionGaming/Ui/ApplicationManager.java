package com.app.evolutionGaming.Ui;

import com.app.evolutionGaming.Ui.navigationHelper.NavigationService;
import com.app.evolutionGaming.Ui.pageHelper.*;
import com.app.evolutionGaming.Ui.selectorHelper.SelectorService;
import com.app.evolutionGaming.framework.parameters.Parameters;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.app.evolutionGaming.Ui.types.Languages.*;
import static com.app.evolutionGaming.Ui.types.Timeouts.DEFAULT_TIMEOUT_SECS;
import static com.app.evolutionGaming.Ui.types.Timeouts.INCREASED_TIMEOUT_SECS;
import static java.lang.Thread.sleep;
import static org.openqa.selenium.remote.BrowserType.*;

@Log4j
public class ApplicationManager {

    public static WebDriver driver;
    private String OS = System.getProperty("os.name").toLowerCase();
    private SelectorService selectorService;
    private ValidLinksService validLinksService;
    private VacanciesPageService vacanciesPageService;
    private AnnouncementService announcementService;
    private MemoPageService memoPageService;
    private NavigationService navigationService;
    private SearchPageService searchPageService;
    private MainPage mainPage;

    public void init() {
        switch (Parameters.instance().getBrowser()) {
            case FIREFOX:
                //dummy
            case IE:
                //dummy
            case CHROME:
                driver = new ChromeDriver(getChromeDriver());
                break;
            default:
                log.error("Please enter a browser type. (By default: Chrome)");
        }
        long start = System.currentTimeMillis();
        GetBaseUrl();
        long finish = System.currentTimeMillis();
        long totalTimeInMillis = finish - start;
        double seconds = (totalTimeInMillis / 1000.0) % 60;
        double minutes = (double) ((totalTimeInMillis / (1000 * 60)) % 60);
        log.info("Total time to load the page -> " + "milliseconds: " + totalTimeInMillis + " minutes:" + minutes + " seconds:" + seconds); //Counting time to open the page

        selectorService = new SelectorService(driver);
        selectorService.pageLoad_Timeout(DEFAULT_TIMEOUT_SECS);
        validLinksService = new ValidLinksService(driver);
        vacanciesPageService = new VacanciesPageService(driver);
        announcementService = new AnnouncementService(driver);
        memoPageService = new MemoPageService(driver);
        navigationService = new NavigationService(driver);
        searchPageService = new SearchPageService(driver);
        mainPage = new MainPage(driver);
    }

    private ChromeOptions getChromeDriver() {
        /*
         * open browser (GoogleChrome) by default
         */
        ChromeOptions chromeOptions = new ChromeOptions();
        // Prevent info bars from appearing.
        chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
        chromeOptions.addArguments("--disable-infobars");
        // Disable extensions.
        chromeOptions.addArguments("--disable-extensions");
        // Disables GPU hardware acceleration. If software renderer is not in place, then the GPU process won't launch.
        chromeOptions.addArguments("--disable-gpu");
        // Disables the sandbox for all process types that are normally sandboxed (bypass OS security modelData) - this is
        // necessary within the Docker environment otherwise you will get "NoSuchSession" exception
        chromeOptions.addArguments("--no-sandbox");
        // Disables the use of a zygote process for forking child processes. Instead, child processes will be forked and
        // exec'd directly. Note that --no-sandbox should also be used together with this flag because the sandbox needs the
        // zygote to work.
//        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--no-zygote");
        // Overcome limited resource problems
        chromeOptions.addArguments("--disable-dev-shm-usage");

        if (OS.startsWith("windows")) {
            System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriverWin());
            if (Parameters.instance().getHeadless().toLowerCase().equals("true")) {
                chromeOptions.addArguments("--headless");
            }
        } else if (OS.startsWith("linux")) {
            System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriverLin());
            if (Parameters.instance().getHeadless().toLowerCase().equals("true")) {
                chromeOptions.addArguments("--headless");
            }
        }
        // Set max. dimensions of the browser window
        chromeOptions.addArguments("window-size=1920,1080");

        return chromeOptions;
    }

    public void GetBaseUrl() {
        switch (Parameters.instance().getLanguage()) {
            case ENGLISH:
                driver.get(Parameters.instance().getUrl() + ENGLISH);
                break;
            case RUSSIAN:
                driver.get(Parameters.instance().getUrl() + RUSSIAN);
                break;
            case LATVIAN:
                driver.get(Parameters.instance().getUrl() + LATVIAN);
                break;
            default:
                log.error("Please choose website language");
        }
    }

    public void stop() throws InterruptedException {
        sleep(INCREASED_TIMEOUT_SECS);
        driver.quit();
    }

    public AnnouncementService getAnnouncementService() {
        return announcementService;
    }

    public MemoPageService getMemoPageService() {
        return memoPageService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public SelectorService getSelectorService() {
        return selectorService;
    }

    public ValidLinksService getValidLinksService() {
        return validLinksService;
    }

    public VacanciesPageService getVacanciesPageService() {
        return vacanciesPageService;
    }

    public SearchPageService getSearchPageService() {
        return searchPageService;
    }

    public MainPage getMainPage() {return mainPage;}
}
