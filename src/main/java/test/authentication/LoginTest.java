package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.components.global.authentication.LoginDialogComponent;
import models.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            LoginPage loginPage = new LoginPage(androidDriver);
            BottomNavComponent bottomNavComponent = new BottomNavComponent(androidDriver);
            //Go to Login form
            bottomNavComponent.clickLoginLabel();

            //fill in login form
            loginPage.userNameElem().sendKeys("teo@sth.com");
            loginPage.passElem().sendKeys("87654321");
            loginPage.clickLoginBtn();

            //verify success message
            String message = loginPage.loginDialogComponent().messageTitle();
            System.out.println(message);

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
