package lesson17;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

public class HandleMultipleApp {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            List<MobileElement> credsFormElems = androidDriver.findElementsByXPath("//android.widget.EditText");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX = 1;
            credsFormElems.get(EMAIL_INPUT_INDEX).sendKeys("Teo@sth.com");
            credsFormElems.get(PASSWORD_INPUT_INDEX).sendKeys("87654321");
            loginBtnElem.click();

            // Put webdriverio demo app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Open Settings application
            androidDriver.activateApp("com.android.settings");

            //do sth here
            androidDriver.findElementByXPath("//*[@text='Connections']").click();
            androidDriver.findElementByXPath("//*[@text='Wi-Fi']").click();

            MobileElement wifiSwichBtnElems = androidDriver.findElement(By.id("com.android.settings:id/switch_widget"));
            boolean isWifiOn = wifiSwichBtnElems.getText().equals("ON");
            if(isWifiOn){
                // Change to OFF
                wifiSwichBtnElems.click();
                Thread.sleep(3000);
                //Change to ON again
                wifiSwichBtnElems.click();
            } else {
                wifiSwichBtnElems.click();
            }

            //IF ON -> OFF and then ON | ELSE -> ON

            // re-launch WebDriverIO App
            Thread.sleep(5000);
            androidDriver.activateApp("com.wdiodemoapp");

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
