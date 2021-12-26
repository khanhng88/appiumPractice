package lesson16;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson14.DriverFactory;

public class HandleHybridContext {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement webviewLabel = androidDriver.findElementByAccessibilityId("Webview");
            webviewLabel.click();

            androidDriver.getContextHandles().forEach(context->{
                System.out.println(context);
            });
        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
