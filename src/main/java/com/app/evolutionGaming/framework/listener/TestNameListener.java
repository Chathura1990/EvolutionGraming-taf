package com.app.evolutionGaming.framework.listener;

import lombok.extern.log4j.Log4j;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.app.evolutionGaming.Ui.ApplicationManager.driver;

@Log4j
public class TestNameListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext tr) {
        super.onStart(tr);
        log.info("");
        log.info("[TEST STARTED] - " + tr.getName());
        log.info("");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        log.info("[TEST SUCCESSFULLY PASSED]");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        log.info("[TEST FAILED]");
        String timeStamp;
        File screenShotName;
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if (ITestResult.FAILURE == tr.getStatus()) {
            try {
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                // Call method to capture screenshot
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                //Adding timestamp
                timeStamp = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(Calendar.getInstance().getTime());
                // Copy files to specific location
                Path currentRelativePath = Paths.get("");//getting current path
                String path1 = currentRelativePath.toAbsolutePath().toString();
                screenShotName = new File(path1 + "/test-output/ScreenshotOfTheError/" + timeStamp + " " + tr.getName() + ".png");
                FileUtils.copyFile(src, screenShotName);
                String filePath = screenShotName.toString();
                //String path = ("<img src='"+filePath+"' height='400' width='750'/>");
                log.info("Successfully captured a screenshot");
                log.info("Screenshot name and path: " + filePath);
            } catch (Exception e) {
                log.info("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        log.info("[TEST SKIPPED]");
    }
}
