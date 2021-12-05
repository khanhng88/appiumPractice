package lesson19;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class LoginTest {

    @Ignore
    @Test
    public void loginWithCorrectCreds() {
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
            String actualMessage = loginPage.loginDialogComponent().messageTitle();
            Assert.assertEquals(actualMessage, "Success", "[ERR] Wrong actual message");
            System.out.println(actualMessage);

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }

    @Test (dependsOnMethods = {"a3"})
    public void a2() {
        System.out.println("a2");
    }

    @Test
    public void a3() {
        Assert.assertTrue(true);
    }

}
