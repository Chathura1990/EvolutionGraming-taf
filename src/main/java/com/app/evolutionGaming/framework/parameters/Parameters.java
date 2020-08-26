package com.app.evolutionGaming.framework.parameters;

import com.app.evolutionGaming.Ui.types.Languages;
import com.beust.jcommander.Parameter;
import org.openqa.selenium.remote.BrowserType;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.app.evolutionGaming.framework.parameters.GlobalParameters.BASE_URL;

public class Parameters {

    private static Parameters instance;

    private Path currentRelativePath = Paths.get("");//get current path
    private String path1 = currentRelativePath.toAbsolutePath().toString();

    @Parameter(names = {"--chromeLin", "-c"}, description = "Path to Chrome Driver for linux or ubuntu users")
    private String chromeDriverLin = path1 + "/src/main/resources/driver/chromedriver";

    @Parameter(names = {"--chromeWin", "-w"}, description = "Path to Chrome Driver for windows users")
    private String chromeDriverWin = path1 + "/src/main/resources/driver/chromedriver.exe";

    @Parameter(names = {"--url", "-u"}, description = "URL")
    private String url = BASE_URL;

    @Parameter(names = {"--browser", "-b"}, description = "Browser by default")
    private String browser = BrowserType.CHROME;

    @Parameter(names = {"--lang", "-l"}, description = "Website Language by default")
    private String language = Languages.ENGLISH;

    @Parameter(names = "--help", help = true, description = "How to use")
    private boolean help;

    @Parameter(names = "--headless", description = "If flag set to 'true' Browser will be started in headless mode (required for running on server)")
    private String headless = "false";

    public static synchronized Parameters instance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    public String getUrl() {
        return url;
    }

    public String getChromeDriverWin() {
        return chromeDriverWin;
    }

    public String getChromeDriverLin() {
        return chromeDriverLin;
    }

    public String getBrowser() {
        return browser;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isHelp() {
        return help;
    }

    public String getHeadless() {
        return headless;
    }

}
