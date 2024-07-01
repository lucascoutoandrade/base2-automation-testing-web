package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.BasePage;
import core.DriverFactory;

public class ViewIssuePage extends BasePage {

	@FindBy(xpath = "//a[contains(text(),'Print Reports')]")
	WebElement printReportsButton;

	@FindBy(xpath = "//a[contains(text(),'CSV Export')]")
	WebElement csvReportButton;

	@FindBy(xpath = "//a[contains(text(),'Excel Export')]")
	WebElement excelReportButton;
	
	@FindBy(xpath = "//div[@class='center']")
	WebElement tittlePrint;
	
	@FindBy(css = "div.widget-box:nth-child(1) > div:nth-child(1) > h4")
	WebElement tittleViewIssueDetails;
	
	

	public ViewIssuePage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}

	public void clickOnPrintReports() {
		click(printReportsButton);
	}

	public void clickOnCSVReports() {
		click(csvReportButton);
	}

	public void clickOnExcelReports() {
		click(excelReportButton);

	}
	
	public String getTittlePagePrint() {
		
		return tittlePrint.getText();
		
	}
	
	public String getTittleViewIssueDetails() {
		
		
		return tittleViewIssueDetails.getText();
	}

}
