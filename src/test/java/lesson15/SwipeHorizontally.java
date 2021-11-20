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

public class SwipeHorizontally {

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
            int xEndPoint = 10 * screenWidth / 100;
            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = yStartPoint;

            //Convert to coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //perform swipe action
            int MAX_SWIPE = 5;
            int start = 0;
//            boolean notStopSwipe = true;

            while (start < MAX_SWIPE ){
                    List<MobileElement> cards = androidDriver.findElementsByXPath("//*[contains(@text,'EXTENDABLE')]");
                    if(!cards.isEmpty()) {
                       break;
                    }

                    TouchAction touchAction = new TouchAction(androidDriver);
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                start++;
            }

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
