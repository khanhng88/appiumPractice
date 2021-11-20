package lesson14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

    }

    public static void stopAppiumServer() {
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

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        //set up desire caps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

        androidDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        return androidDriver;
    }
}
