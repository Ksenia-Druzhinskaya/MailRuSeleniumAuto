package lib;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static lib.DriverManager.getDriver;
import static lib.TestData.MAIL_RU_URL;

public class CoreTestCase
{
    protected WebDriver driver;

    @Before
    public void setUp() throws Exception{
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(MAIL_RU_URL);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
