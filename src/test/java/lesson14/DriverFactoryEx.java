package lesson14;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lesson16.AndroidServerFlagEx;
import lesson16.MobileCapabilityTypeEx;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactoryEx {

    private static AppiumDriverLocalService appiumServer;
    private static AppiumDriver<MobileElement> appiumDriver;

    /**
     * Get Appium driver
     * @return
     */
    public AppiumDriver<MobileElement> getAppiumDriver() {
        if(appiumDriver == null) {
            appiumDriver = initAppiumDriver();
        }
        return appiumDriver;
    }

    /**
     * Init appium Driver and set capabilities
     * @return
     */
    private AppiumDriver<MobileElement> initAppiumDriver() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver.autodownload");
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return appiumDriver;
    }

    /**
     * Quit appium session
     */
    public void quitAppiumSession() {
        if(appiumDriver!=null) {
            appiumDriver.quit();
//            appiumDriver=null;
            stopAppiumServer();
        }

    }

    /**
     * Stop Appium Server
     */
    private static void stopAppiumServer() {
        String killNodeWinCommand = "taskkill /F /IM node.exe";
        String killNodeLinuxCommand = "killall node";

        String killNodeCommand = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWinCommand : killNodeLinuxCommand;

        Runtime runtime = Runtime.getRuntime();
        try{
            runtime.exec(killNodeCommand);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
