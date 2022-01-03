package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class TakingScreenShot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            // Wait until we are on Login screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement loginBtnElems = androidDriver.findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtnElems));

            // Taking whole screenshot
            File base64ScreenShotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginForm.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));

            // Taking element screenshot FAB - floating action button
            File base64LoginBtnData = loginBtnElems.getScreenshotAs(OutputType.FILE);
            String loginBtnImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginBtnElems.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnImgFileLocation));

            // Taking area screenshot
            List<MobileElement> viewGroupElms = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if (!viewGroupElms.isEmpty()) {
                File base64NavData = viewGroupElms.get(viewGroupElms.size()-1).getScreenshotAs(OutputType.FILE);
                String navImgLocation = System.getProperty("user.dir").concat("/screenshots/").concat("nav.png");
                FileUtils.copyFile(base64NavData,new File(navImgLocation));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }

}
