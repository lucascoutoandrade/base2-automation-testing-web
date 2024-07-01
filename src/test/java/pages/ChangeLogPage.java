package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.BasePage;
import core.DriverFactory;

public class ChangeLogPage extends BasePage {
	
	@FindBy(css = ".lead")
	WebElement textPage;
	

	public ChangeLogPage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}
	
	public String getInformationPage() {
		
		return textPage.getText();
		
	}

}
