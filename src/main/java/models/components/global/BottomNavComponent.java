package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private By loginLabelSel = MobileBy.AccessibilityId("Login");
    private By formLabelSel = MobileBy.AccessibilityId("Forms");
    private By swipeLabelSel = MobileBy.AccessibilityId("Swipe");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement loginLabelElem() {
        return this.appiumDriver.findElement(loginLabelSel);
    }

    public MobileElement formLabelElem() {
        return this.appiumDriver.findElement(formLabelSel);
    }

    public MobileElement swipeLabelElem() {
        return this.appiumDriver.findElement(swipeLabelSel);
    }

    public void clickLoginLabel() {
        loginLabelElem().click();
    }
}
