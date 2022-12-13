package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static lib.TestData.CHROME;
import static lib.TestData.FIREFOX;

public class DriverManager
{
    public static WebDriver getDriver() throws Exception{
        String currentBrowser = getBrowserVar();
        switch (currentBrowser){
            case CHROME :
                return new ChromeDriver();
            case FIREFOX :
                return new FirefoxDriver();
            default :
                throw new Exception("Cannot detect type of the Driver. Browser value is " + getBrowserVar());
        }
    }

    private static String getBrowserVar(){
        return System.getenv("BROWSER");
    }
}
