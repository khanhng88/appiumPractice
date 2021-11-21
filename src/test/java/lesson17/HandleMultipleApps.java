package lesson17;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson14.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            //enter email and password
            MobileElement emailInputElem = androidDriver.
                    findElementByXPath("//android.widget.EditText[@content-desc=\"input-email\"]");
            MobileElement passInputElem = androidDriver.
                    findElementByXPath("//android.widget.EditText[@content-desc=\"input-password\"]");
            MobileElement loginBtnElem = androidDriver.
                    findElementByAccessibilityId("button-LOGIN");

            emailInputElem.sendKeys("teo@sth.com");
            passInputElem.sendKeys("87654321");
            loginBtnElem.click();

            //put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            //open setting app on device
            androidDriver.activateApp("com.android.settings");
            Thread.sleep(2000);

            MobileElement networkAndInternetElem =
                    androidDriver.findElementByXPath("//*[contains(@resource-id," +
                            "'com.android.settings:id/dashboard_tile') and contains(@index,'1')]");
            networkAndInternetElem.click();
            WebDriverWait wait = new WebDriverWait(androidDriver, 2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Wi‑Fi')]")));
            MobileElement wifiSwitch = androidDriver.findElementByXPath("//android.widget.Switch[@content-desc=\"Wi‑Fi\"]");
            boolean isWifiON = wifiSwitch.getAttribute("text").equals("ON");

            if(isWifiON) {
                wifiSwitch.click();
                Thread.sleep(2000);
                wifiSwitch.click();
            }

            androidDriver.activateApp("com.wdiodemoapp");

        }catch (Exception e){

        }
    }
}
