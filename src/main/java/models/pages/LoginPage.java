package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.global.authentication.LoginDialogComponent;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private By usernameSel = MobileBy.AccessibilityId("input-email");
    private By passSel = MobileBy.AccessibilityId("input-password");
    private By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement userNameElem() {
        return this.appiumDriver.findElement(usernameSel);
    }

    public MobileElement passElem() {
        return this.appiumDriver.findElement(passSel);
    }

    public MobileElement loginBtnElem() {
        return this.appiumDriver.findElement(loginBtnSel);
    }

    public void clickLoginBtn() {
        loginBtnElem().click();
    }

    public BottomNavComponent bottomNavComponent() {
        return new BottomNavComponent(appiumDriver);
    }

    public LoginDialogComponent loginDialogComponent() {
        return new LoginDialogComponent(appiumDriver);
    }
}
