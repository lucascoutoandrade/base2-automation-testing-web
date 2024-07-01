package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.BasePage;
import core.DriverFactory;

public class RoadMapPage extends BasePage {
	
	
	
	@FindBy(css = ".lead")
	WebElement textPage;
	

	public RoadMapPage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}
	
	public String getInformationPage() {
		
		return textPage.getText();
		
	}

}
