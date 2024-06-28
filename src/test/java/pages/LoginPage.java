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

	public void waitHomePageLoaded() {

		waitElementVisible(isHome, 20);
	}

	public boolean isLoginPage() {

		try {

			waitElementVisible(username, 05);
			return true;
			
		} catch (NoSuchElementException e) {
			
			log.warning("ERROR: "+e.getLocalizedMessage());
			return false;

		}

	}

}
