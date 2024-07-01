package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.BasePage;
import core.DriverFactory;

public class HomePage extends BasePage {

	@FindBy(xpath = "//span[@class='user-info']")
	WebElement userInfo;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutButton;
	
	@FindBy(xpath = "//a[@class='btn btn-primary btn-sm']")
	WebElement reportIssueButton;
	
	@FindBy(xpath = "//i[@class='menu-icon fa fa-list-alt']")
	WebElement viewIssueButton;
	
	
	
	
	public HomePage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}

	public void clickOnUserInfo() {

		click(userInfo);

	}

	public void clickOnLogoutButton() {

		click(logoutButton);

	}
	
	public void clickOnReporterIssueButton() {
		
		click(reportIssueButton);
	}
	
public void clickOnViewIssueButton() {
		
		click(viewIssueButton);
	}

}
