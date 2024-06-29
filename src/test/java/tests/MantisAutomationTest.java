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

	public final String username = "Lucas_Andrade";
	public final String password = "1234";
	private LoginPage loginPage = new LoginPage();
	private HomePage homePage = new HomePage();

	@Test
	@Order(1)
	public void test01_loginSucess() throws InterruptedException {

		// Fill usernameField
		loginPage.enterUsername(username);
		
		// Click on Login Button
		loginPage.clickOnLoginBtn();
		

		loginPage.enterPassword(password);
		
		// Click on Login Button
		loginPage.clickOnLoginBtn();

		// Wait until element appears
		loginPage.waitHomePageLoaded();


	}
	
	@Test
	@Order(2)
	public void test02_logOut ()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnUserInfo();
		homePage.clickOnLogoutButton();
		Assertions.assertTrue(loginPage.isLoginPage());
		
	}


}
