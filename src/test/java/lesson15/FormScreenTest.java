package lesson15;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson14.DriverFactory;

public class FormScreenTest {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            //click form label
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement formLabelElem = androidDriver.findElementByAccessibilityId("Forms");
            formLabelElem.click();

            //click active button
            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
