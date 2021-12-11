package lesson16;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class DeviceKeyAction {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement loginlabel = appiumDriver.findElementByAccessibilityId("Login");
            loginlabel.click();
            Thread.sleep(4000);

            // Back to home screen
            appiumDriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

            Thread.sleep(2000);

        } catch (Exception ignored) {

        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
