package com.app.evolutionGaming.Ui.testBase;

import com.app.evolutionGaming.Ui.ApplicationManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setup_Application() {
        app.init();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        app.stop();
    }

    //Convert all the test cases in TestNG report to Chronological order.
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) {
        try {
            Priority priority = method.getAnnotation(Priority.class);
            String testMethodName = String.format("%s - %s", priority, method.getName());
            Field methodName = org.testng.internal.BaseTestMethod.class.getDeclaredField("m_methodName");
            methodName.setAccessible(true);
            methodName.set(result.getMethod(), testMethodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    public @interface Priority {
        int value() default 0;
    }
}
