package lesson20;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lesson14.DriverFactoryEx;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    //thread safe
    //synchronized list -> tranh bi conflict cac thread
    //local thread de isolate tests
    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;

    @BeforeSuite (alwaysRun = true)
    public static void beforeSuite() {
        driverThread = ThreadLocal.withInitial(()->{
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        for (DriverFactoryEx driverFactoryEx : driverThreadPool) {
            driverFactoryEx.quitAppiumSession();
        }

    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
}
