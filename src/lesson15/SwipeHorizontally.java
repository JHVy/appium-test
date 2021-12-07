package lesson15;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SwipeHorizontally {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        int NUMBER_OF_SWIPE_SCREEN = 5;

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Click on Forms label
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            formsLabelElem.click();

            // Check to see weather ew are on
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("Swipe-screen")));

            //Get Mobile windows size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Calculate touch point
            int xStartPoint = 90*screenWidth/100;
            int xEndPoint = 10*screenWidth/100;
            int yStartPoint = 70*screenHeight/100;
            int yEndPoint = yStartPoint;

            //Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform touch actions
            TouchAction touchAction = new TouchAction(androidDriver);

            //Swipe screen (left <- right)
            for (int index = 0; index < NUMBER_OF_SWIPE_SCREEN; index++){
                List<MobileElement> supportVideoPopup = androidDriver.findElementsByXPath("//*[@text='SUPPORT VIDEOS']");
                if (supportVideoPopup.isEmpty()){
                   touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
        DriverFactory.stopAppiumServer();
        }
    }
}
