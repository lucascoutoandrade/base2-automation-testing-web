package core;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class BasePage {

	public Logger log = Logger.getLogger("QALogger");

	public void waitElementVisible(WebElement element, int segundos) {
		Wait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(segundos))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		log.info("element: " + element + " found successfully!");
	}

	public BasePage insertText(WebElement el, String text) {
		el.sendKeys(text);
		log.info("text: " + text + " filled successfully!");
		return this;

	}
	
	public BasePage selectByText(WebElement el, String text) {
		
		Select select  = new Select(el);
		select.selectByVisibleText(text);
		log.info("text:"+text+" selected successfully!");
		return this;
		
	}
	
public BasePage selectByValue(WebElement el, String value) {
		
		Select select  = new Select(el);
		select.selectByValue(value);
		log.info("value:"+value+" selected successfully!");
		return this;
		
	}

	public BasePage click(WebElement el) {
		el.click();
		log.info("element: " + el + " clicked!");
		return this;

	}

	public void clickText(String texto) {
		DriverFactory.getDriver().findElement(By.xpath("//*[text()='" + texto + "']")).click();
		log.info("text: " + texto + " clicked!");

	}

	public void scrollToElement(WebElement elemento) {
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
		waitElementVisible(elemento, 5);
		log.info("Scroll: Scroll to element successfully!");

	}

}
