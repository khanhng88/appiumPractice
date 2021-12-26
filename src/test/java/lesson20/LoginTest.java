package lesson20;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdata.LoginCreds;

public class LoginTest extends BaseTest{

    @Test()
    public void loginWithCreds() {
        //init driver
            AppiumDriver<MobileElement> androidDriver = getDriver();

            LoginPage loginPage = new LoginPage(androidDriver);
            BottomNavComponent bottomNavComponent = new BottomNavComponent(androidDriver);
            //Go to Login form
            bottomNavComponent.clickLoginLabel();

            //fill in login form
            loginPage.userNameElem().sendKeys("abc@mail.com");
            loginPage.passElem().sendKeys("12345678");
            loginPage.clickLoginBtn();

            //verify success message
            String actualMessage = loginPage.loginDialogComponent().messageTitle();
            Assert.assertEquals(actualMessage, "Success", "[ERR] Wrong actual message");
            System.out.println(actualMessage);

    }

//    @DataProvider
//    public LoginCreds[] loginCredsData() {
//        String jsonLocation = "/src/test/java/testdata/loginCreds.json";
//        return DataObjectBuilder.buildDataObject(jsonLocation, LoginCreds[].class);
//    }

}
