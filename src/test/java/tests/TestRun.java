package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.OperatingSystem;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.utility.RandomString;

public class TestRun {

	static WebDriver driver;
	public final static String url = "https://thinking-tester-contact-list.herokuapp.com/";
	public final String password = "12345678";

	@BeforeAll
	public static void setUp() {

		WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

	}

	@Test
	public void test01_SignUp() throws InterruptedException {

		// Click on Sign up button
		driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();

		// Get the header page
		String namePage = driver.findElement(By.cssSelector(".main-content > h1")).getText();

		// variables
		String nameUser = "test_";
		String lastNameUser = new RandomString(10).nextString();
		String email = nameUser + lastNameUser + "@test.com";

		System.out.println("name: " + nameUser + " \nlastNameUser: " + lastNameUser + " \nemail: " + email);

		// Check if the correct page
		Assertions.assertEquals("Add User", namePage);

		// Fill the form
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(nameUser);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastNameUser);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		// Wait until element appears
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header:nth-child(1) > h1")));

		// AssertTest
		namePage = driver.findElement(By.cssSelector("header:nth-child(1) > h1")).getText();

		Assertions.assertEquals("Contact List", namePage);

//		Thread.sleep(5000);

	}

	@Test
	public void test02_createContact() throws InterruptedException {

		setUp();
		
		test01_SignUp();

		// Click on button
		driver.findElement(By.xpath("//button[contains(text(),'Add a New Contact')]")).click();

		Thread.sleep(5000);
		
		//input[@id='firstName']
		//input[@id='lastName']
		//input[@id='birthdate']
		//input[@id='email']
		//input[@id='phone']
		//input[@id='street1']
		//input[@id='street2']
		//input[@id='city']
		//input[@id='stateProvince']
		//input[@id='postalCode']
	    //input[@id='country']

	}

	@AfterEach
	public void tearDownTest() {

		driver.close();

	}

	@AfterAll
	public static void tearDown() {

		driver.quit();
	}

}
