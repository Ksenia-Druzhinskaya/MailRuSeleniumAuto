package lib.ui;

import lib.entities.EmailMessage;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.TestData.EMAIL_SENT_TEXT;

public class InboxPageObject extends MainPageObject
{
    private static final String
                COMPOSE_BUTTON = "css:.compose-button__txt",
                SUBJECT_INPUT = "xpath://div[contains(@class,'subject__container')]//input",
                TO_INPUT = "xpath://div[contains(@class,'contactsContainer')]//input",
                BODY_INPUT = "xpath://div[contains(@class,'editable-container')]/div/div",
                SEND_BUTTON = "xpath://button[@data-test-id='send']",
                EMAIL_SENT_LABEL = "xpath://a[text()='{TEXT}']";

    protected static String getLabelElement() {
        return EMAIL_SENT_LABEL.replace("{TEXT}", EMAIL_SENT_TEXT);
    }

    public InboxPageObject(RemoteWebDriver driver){super(driver);
    }

    public void sendEmailAndVerify(){
        this.waitForElementClickAndWaitForNotPresent(SEND_BUTTON, "Cannot find Send button");
        this.assertElementPresent(getLabelElement(), "'Email is sent' message is not displayed'" );
    }

    public void clickComposeButton(){
        this.waitForElementAndClick(COMPOSE_BUTTON, "Cannot find Compose button");
    }

    public void fillEmail(EmailMessage email){
        this.waitForElementAndSendKeys(TO_INPUT, email.getTo(), "Cannot find To input");
        this.waitForElementAndSendKeys(SUBJECT_INPUT, email.getSubject(), "Cannot find Subject input");
        this.waitForElementAndSetValue(BODY_INPUT, email.getHtmlBody(), "Cannot find and put a login to the login input");
    }
}
