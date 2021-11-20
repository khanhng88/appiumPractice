package lesson15;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lesson14.DriverFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeVerticallyForLogo {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            //click form label
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement formLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            formLabelElem.click();

            //wait some second to see switch
            WebDriverWait wait = new WebDriverWait(androidDriver, 1);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[contains(@text, 'Swipe horizontal')]")));

            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //calculate touchpoint
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            //Convert to coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            int MAX_SWIPE_DOWN= 3;
            int swipeTime = 0;
            boolean found = false;
            while(swipeTime < MAX_SWIPE_DOWN) {
                try {
                    MobileElement logo = androidDriver.findElementByAccessibilityId("WebdriverIO logo");
                    if(logo.isDisplayed()){
                        found = true;
                        break;
                    }
                }catch (Exception e){
                    TouchAction touchAction = new TouchAction(androidDriver);
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                }
                swipeTime++;
            }
            if(found == false){
                System.out.println("unable to find logo");
            }
        }catch (Exception e){}finally {

            DriverFactory.stopAppiumServer();
        }
    }
}
