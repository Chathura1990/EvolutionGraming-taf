# Evolution-Gaming-SS.com-taf

Test Automation Framework provides the ability to automate UI tests for SS.com.

## Technologies
- Java 8
- TestNG
- Selenium WebDriver
- Maven
- Lombok
-JCommander

## Browser Support
1. **Chrome** *(used by default)*
2. **Chrome Headless**

List of supported browsers could be extended by implementing driver initialization in `BrowserInstance`:

```java
public class ApplicationManager {
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
}
    //...
}
```
---

## How to run tests

In terminal without parameters:
```cmd
mvn clean test 
```

In terminal with parameters:  
_for more details please check:   `src/main/java/com/app/evolutionGaming/framework/parameters/Parameters.java`_  
```cmd
mvn clean package
java -jar target/SS.com-1.0-SNAPSHOT-jar-with-dependencies.jar \
--browser {browser name}
--chromeLin or --chromeWin {path to driver}
--lang {site language "currently working for English"}
```
---
or you can run:   `src/main/java/com/app/evolutionGaming/framework/main/TestNGRunner.java`  
Test files are stored in:   
`src/main/java/com/app/evolutionGaming/tests/` 

## Logging
Log4j logging is used in TAF.

```java
import lombok.extern.log4j.Log4j;

@Log4j
 public class LogExample {
    public void methodExample() {
        log.info("Example");
    }
 }
```

---




