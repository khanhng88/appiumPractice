package lesson16;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lesson14.DriverFactory;

public class DriverKeyAction {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            Thread.sleep(2000);

            androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
            Thread.sleep(2000);

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
