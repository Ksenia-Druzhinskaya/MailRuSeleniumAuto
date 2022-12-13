package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
                LOGIN_BUTTON = "css:.resplash-btn_primary",
                LOGIN_FRAME = "xpath://iframe[contains(@src,'account.mail.ru')]",
                PASSWORD_BUTTON = "xpath://button[@data-test-id='next-button']",
                EMAIL_INPUT = "xpath://input[@name='username']",
                PASSWORD_INPUT = "xpath://input[@name='password']",
                SUBMIT_BUTTON = "xpath://button[@data-test-id='submit-button']";

    public AuthorizationPageObject(RemoteWebDriver driver){super(driver);
    }

    public void enterLoginDataAndSubmit(String login, String password){

        this.switchToLoginFrame();

        this.waitForElementAndSendKeys(EMAIL_INPUT, login, "Cannot find and put a login to the login input");
        this.waitForElementClickAndWaitForNotPresent(PASSWORD_BUTTON, "Cannot find Password button");
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input");
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button.");

        this.switchOffLoginFrame();
    }

    public void clickLoginButton(){
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find Login button");;
    }

    public void logIn(String login, String password){
        this.clickLoginButton();
        this.enterLoginDataAndSubmit(login, password);
    }

    private void switchToLoginFrame(){
        WebElement loginFrame = this.waitForElementPresent(LOGIN_FRAME, "Login frame is not displayed");
        driver.switchTo().frame(loginFrame);
    }

    private void switchOffLoginFrame(){
        driver.switchTo().defaultContent();
    }
}
