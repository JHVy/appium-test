package utils.screen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeUntil {
    public static void verticallyUntilISee(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            //Click on Forms label
            MobileElement formsLabelElem = androidDriver.findElementByAccessibilityId("Forms");
            formsLabelElem.click();

            // Check to see weather ew are on
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

            //Get Mobile windows size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //Calculate touch point
            int xStartPoint = 50*screenWidth/100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90*screenHeight/100;
            int yEndPoint = 10*screenHeight/100;

            //Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Perform touch actions
            TouchAction touchAction = new TouchAction(androidDriver);

            //Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //Swipe down
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();

            //Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //Click on [Active] button
            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
        DriverFactory.stopAppiumServer();
        }
    }

    public static void main(String[] args) {
        By selector;
        SwipeUntil.verticallyUntilISee(selector);
        SwipeUntil.verticallyUntilISee(selector, percentage);
        SwipeUntil.verticallyUntilISee(selector, percentage, maxTimes);
        SwipeUntil.horizontallyUntilISee(selector);
        SwipeUntil.horizontallyUntilISee(selector, percentage);
        SwipeUntil.horizontallyUntilISee(selector, percentage, maxTimes);
    }
}
