package lesson17;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lesson14.DriverFactory;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeForNotifications {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            //get coordinates
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //perform swipe
            TouchAction touchAction = new TouchAction(androidDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //add notifications to list
            List<MobileElement> notificationsElems = androidDriver.findElementsById("android:id/notification_main_column");
            if(notificationsElems.isEmpty())
                throw new RuntimeException("Notification list is empty");
            Map<String, String> notificationList = new HashMap<>();

            notificationsElems.forEach(notification->{
                String notificationTitle = notification.findElementById("android:id/title").getText();
                String notificationContent = notification.findElementById("android:id/big_text").getText();
                notificationList.put(notificationTitle, notificationContent);
            });

            //print out notification list
            notificationList.keySet().forEach(key ->{
                System.out.println(key+" - "+notificationList.get(key));
            });


        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
