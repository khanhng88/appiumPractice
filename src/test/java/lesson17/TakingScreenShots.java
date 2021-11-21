package lesson17;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lesson14.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TakingScreenShots {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();


            WebDriverWait wait = new WebDriverWait(androidDriver, 3);
            MobileElement loginBtnElem = androidDriver.
                    findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtnElem));

//            File base64Screenshotdata = androidDriver.getScreenshotAs(OutputType.FILE);
//            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("form.png");
//            FileUtils.copyFile(base64Screenshotdata, new File(fileLocation));
            TakingScreenShots.takeFullScreenShot(androidDriver);
            //whole screenshot
//            File base64SrnShot = loginBtnElem.getScreenshotAs(OutputType.FILE);;
//            String loginBtnfileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginBtn.png");
//            FileUtils.copyFile(base64SrnShot, new File(loginBtnfileLocation));

            TakingScreenShots.takeElementScreenShot(loginBtnElem);

            //element screenshot
//            List<MobileElement> navElem = androidDriver.
//                    findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
//            if(!navElem.isEmpty()) {
//                File base64NavSrnShot = navElem.get(navElem.size()-1).getScreenshotAs(OutputType.FILE);
//                String navFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("nav.png");
//                FileUtils.copyFile(base64NavSrnShot, new File(navFileLocation));
//            }

        }catch (Exception e){}finally {
            DriverFactory.stopAppiumServer();
        }
    }

    public static void takeFullScreenShot(AndroidDriver<MobileElement> androidDriver) throws IOException {
        File base64Screenshotdata = androidDriver.getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String fileLocation = System.getProperty("user.dir").concat("/screenshots/full/").concat("screenshot_"+ dateFormat.format(date) +".png");
        FileUtils.copyFile(base64Screenshotdata, new File(fileLocation));
    }

    public static void takeElementScreenShot(MobileElement element) throws IOException {
        File base64Screenshot = element.getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("screenshot_"+ dateFormat.format(date) +".png");
        FileUtils.copyFile(base64Screenshot, new File(fileLocation));
    }
}
