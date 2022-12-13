package tests;

import lib.CoreTestCase;
import lib.entities.EmailMessage;
import lib.ui.AuthorizationPageObject;
import lib.ui.InboxPageObject;
import org.junit.Test;

import static lib.TestData.*;

public class MailTests extends CoreTestCase
{
    @Test
    public void sendMessageTest(){
            EmailMessage email = new EmailMessage(TO_VALUE, SUBJECT_VALUE, BODY_TEXT);

            // Login
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.logIn(LOGIN_VALUE, PASSWORD_VALUE);

            // Create and send email
            InboxPageObject inboxPageObject = new InboxPageObject(driver);
            inboxPageObject.clickComposeButton();
            inboxPageObject.fillEmailFields(email);
            inboxPageObject.sendEmailAndVerify();
    }
}
