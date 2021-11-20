package lesson14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactoryTest {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginbtn = androidDriver.findElementByAccessibilityId("Login");
            loginbtn.click();

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

//            MobileElement loginDescription = androidDriver.
//                    findElementByXPath("//*[contains(@text,\"When the device\")]");
//            System.out.println(loginDescription.getText());

            WebDriverWait wait = new WebDriverWait(androidDriver, 3);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
//            MobileElement loginResult = androidDriver.findElementById("android:id/alertTitle");
//            System.out.println(loginResult.getText());
        }catch (Exception e){

        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
