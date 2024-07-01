package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.BasePage;
import core.DriverFactory;
import net.bytebuddy.utility.RandomString;

public class IssueDetailsPage extends BasePage {

	@FindBy(xpath = "//select[@id='category_id']")
	WebElement category;

	@FindBy(xpath = "//input[@id='summary']")
	WebElement summary;

	@FindBy(xpath = "//textarea[@id='description']")
	WebElement description;

	@FindBy(xpath = "//input[@value='Submit Issue']")
	WebElement submitButton;

	private String randomValue = "test_" + new RandomString(10).nextString();

	public IssueDetailsPage() {

		PageFactory.initElements(DriverFactory.getDriver(), this);

	}

	public void selectCategory(String valueCategory) {

		waitElementVisible(category, 5);
		selectByText(category, valueCategory);

	}

	public void enterSumary() {

		insertText(summary, randomValue);

	}

	public void enterDescription() {

		insertText(description, randomValue);

	}

	public void clickOnSubmitButton() {

		click(submitButton);
	}

}
