package models.components.global.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDialogComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private By messageTitleSel = MobileBy.id("android:id/alertTitle");


    public LoginDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String messageTitle() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageTitleSel));
        return this.appiumDriver.findElement(messageTitleSel).getText();
    }
}
