package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pages.HomePage;
import pages.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MantisAutomationTest extends BaseTest {

//	static WebDriver driver;
//	public final static String url = "https://mantis-prova.base2.com.br";
	public final String username = "Lucas_Andrade";
	public final String password = "1234";
	private LoginPage loginPage = new LoginPage();
	private HomePage homePage = new HomePage();
//	@BeforeAll
//	public static void setUp() {
//
//		WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get(url);
//
//	}

	@Test
	@Order(1)
	public void test01_loginSucess() throws InterruptedException {

		// Fill usernameField
//		DriverFactory.getDriver().findElement(By.id("username")).sendKeys(username);
		loginPage.enterUsername(username);
		
		// Click on Login Button
//		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginBtn();
		
//		DriverFactory.getDriver().findElement(By.id("password")).sendKeys(password);
		loginPage.enterPassword(password);
		
		// Click on Login Button
//		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Login']")).click();
		loginPage.clickOnLoginBtn();

		// Wait until element appears
//		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='bug_id']")));
		loginPage.waitHomePageLoaded();


//		Thread.sleep(5000);

	}
	
	@Test
	@Order(2)
	public void test02_logOut ()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnUserInfo();
		homePage.clickOnLogoutButton();
		Assertions.assertTrue(loginPage.isLoginPage());
		
	}

//	@Test
//	public void test02_createContact() throws InterruptedException {
//
//		setUp();
//		
//		test01_SignUp();
//
//		// Click on button
//		driver.findElement(By.xpath("//button[contains(text(),'Add a New Contact')]")).click();
//
//		Thread.sleep(5000);
//		
//		//input[@id='firstName']
//		//input[@id='lastName']
//		//input[@id='birthdate']
//		//input[@id='email']
//		//input[@id='phone']
//		//input[@id='street1']
//		//input[@id='street2']
//		//input[@id='city']
//		//input[@id='stateProvince']
//		//input[@id='postalCode']
//	    //input[@id='country']
//
//	}

//	@AfterEach
//	public void tearDownTest() {
//
//		driver.close();
//
//	}
//
//	@AfterAll
//	public static void tearDown() {
//
//		driver.quit();
//	}

}
