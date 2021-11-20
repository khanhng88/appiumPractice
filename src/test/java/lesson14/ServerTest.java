package lesson14;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class ServerTest {

    public static void main(String[] args) {
        //start appiumserver via AppiumServiceBuilder
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);

        AppiumDriverLocalService appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

        //set up desire caps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

        AppiumDriver<MobileElement> appiumDriver = new AndroidDriver<MobileElement>(appiumServer.getUrl(),
                desiredCapabilities);

        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        MobileElement loginbtn = appiumDriver.findElementByAccessibilityId("Login");
        loginbtn.click();

        appiumServer.stop(); //dung cach nay de stop appium server but unstable
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
