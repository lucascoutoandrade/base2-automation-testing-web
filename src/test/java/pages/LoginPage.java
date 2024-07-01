package pages;

import java.util.logging.Logger;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.BasePage;
import core.DriverFactory;

public class LoginPage extends BasePage {

	public Logger log = Logger.getLogger("QALogger");

	@FindBy(id = "username")
	WebElement username;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//input[@name='bug_id']")
	WebElement isHome;
	
	@FindBy(css="div.alert:nth-child(4) > p:nth-child(1)")
	WebElement loginMsgError;
	
	

	public LoginPage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}

	public void enterUsername(String usernameValue) {

		insertText(username, usernameValue);

	}

	public void enterPassword(String passwordValue) {

		insertText(password, passwordValue);

	}

	public void clickOnLoginBtn() {

		click(loginBtn);

	}

	public boolean waitHomePageLoaded() {

		try {

			waitElementVisible(isHome, 20);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean isLoginPage() {

		try {

			waitElementVisible(username, 05);
			return true;

		} catch (NoSuchElementException e) {

			log.warning("ERROR: " + e.getLocalizedMessage());
			return false;

		}

	}
	
	public String getLoginMsgError() {
		
		return loginMsgError.getText();
		
	}

}
