//Read More
//Notes

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class MainClass {

    static WebDriver driver;


    public static void main(String[] args) {
     /*
     System.setProperty("webdriver.chrome.driver", "/absolute/path/to/binary/chromedriver");
     WebDriver webDriver = new ChromeDriver();
     webDriver.get("https://www.google.com");
     */

        String driverType = "chrome";
        driver = setUpDriver(driverType);
        ifDriverNullThrowError(driver);

        navigateTest("http://automationpractice.com/index.php");
//        windowHandlesTest("https://www.naukri.com/");
        quitDriver();

    }

    public static void navigateTest(String url){
        launchURL(url);  // it is essential to add the protocol - Read More
        sleepForSeconds(5);

        WebElement womenApperalsTab = driver.findElement(By.cssSelector("a[title='Women']"));
        womenApperalsTab.click();
        sleepForSeconds(5);
        String currentURL = driver.getCurrentUrl();
        if(!currentURL.equals(url))
            System.out.println("Navigation Successful" + currentURL);
        else
            System.out.println("Navigation Unsuccessful" + currentURL);

        driver.navigate().back();
        sleepForSeconds(5);
        currentURL = driver.getCurrentUrl();
        if(currentURL.equals(url))
            System.out.println("Back Successful" + currentURL);
        else
            System.out.println("Back Unsuccessful" + currentURL);

        driver.navigate().refresh();
        sleepForSeconds(5);
        currentURL = driver.getCurrentUrl();
        if(currentURL.equals(url))
            System.out.println("Refresh Successful" + currentURL);
        else
            System.out.println("Refresh Unsuccessful" + currentURL);

        driver.navigate().forward();
        sleepForSeconds(5);
        currentURL = driver.getCurrentUrl();
        if(!currentURL.equals(url))
            System.out.println("Forward Successful" + currentURL);
        else
            System.out.println("Forward Unsuccessful" + currentURL);


    }

    public static void windowHandlesTest(String url){
        launchURL(url); // it is essential to add the protocol - Read More
        switchToAllWindowsAndPrintTitle();
    }



    private static void launchURL(String url) {
        driver.get(url);
        sleepForSeconds(2);
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
    }

    private static WebDriver setUpDriver(String driverType) {

        WebDriver driver = null;
        if (driverType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();//import io.github.bonigarcia.wdm.WebDriverManager;
            /*
            Notes : Found a problem in launching chrome as it was set for all users,
            WebDriverManager installs latest version and it could not override the all user driver,
            hence deleted "Applications" folder as per below links.
             */
            //https://forum.katalon.com/t/an-administrator-has-installed-google-chrome-on-this-system/34091/3
            //https://stackoverflow.com/questions/42703930/chrome-driver-exception-at-start
            driver = new ChromeDriver();

        } else if (driverType.equalsIgnoreCase("firefox")) {

        }
        return driver;
    }
    private static void ifDriverNullThrowError(WebDriver driver) {
        if (driver == null)
            // throw launches exception under Throwable
            //https://www.geeksforgeeks.org/throw-throws-java/
            throw new NullPointerException("Driver did not get initialised and is null.");
        else
            return;
    }
    private static void switchToAllWindowsAndPrintTitle() {
        String parentWindow = driver.getWindowHandle();// current Window's Handle
        Set<String> allWindows = driver.getWindowHandles(); //getWindowHandles returns all the tabs or windows opened
        System.out.println(allWindows + " All Windows");
        System.out.println(parentWindow + " Parent Window");

        Iterator<String> i = allWindows.iterator();
        while (i.hasNext()) {//runs only one iteration of each element

            /*
             Notes : i.next() returns the current value of Iterator which needs to be stored
                     it also moves the iterator to the next element
             */
            String currentIteratorValue = i.next();// moves iterator to next item
            if (!parentWindow.equals(currentIteratorValue))
                driver.switchTo().window(currentIteratorValue);

            String currentWindowTitle = driver.getTitle();
            System.out.println( " Title: " + currentWindowTitle + " Current Iterator Value: " + currentIteratorValue);
        }
    }


    private static void quitDriver() {
        driver.quit();
    }


    public static void sleepForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
            assertTrue(true);
        } catch (InterruptedException interruptedException) {
            System.out.println("Caught an Interrupted Exception while waiting." + interruptedException.getStackTrace());
        }
    }








}

