package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pages.HomePage;
import pages.IssueDetailsPage;
import pages.LoginPage;
import pages.ViewIssuePage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MantisAutomationTest extends BaseTest {

	public final String username = "Lucas_Andrade";
	public final String password = "1234";
	private LoginPage loginPage = new LoginPage();
	private HomePage homePage = new HomePage();
	private IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
	private ViewIssuePage viewIssuePage = new ViewIssuePage();

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
	
	@Test
	@Order(3)
	public void test03_addIssue()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnReporterIssueButton();
		issueDetailsPage.selectCategory("[All Projects] nova categoria");
		issueDetailsPage.enterSumary();
		issueDetailsPage.enterDescription();
		issueDetailsPage.clickOnSubmitButton();
		
	}
	
	@Test
	@Order(4)
	public void test04_printReports()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnViewIssueButton();
		viewIssuePage.clickOnPrintReports();
		
		Assertions.assertEquals("MantisBT - Lucas Andrade´s Project\"", viewIssuePage.getTittlePagePrint());
		
		
	}
	
	@Test
	@Order(5)
	public void test05_csvReports()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnViewIssueButton();
		viewIssuePage.clickOnCSVReports();
	}
	
	@Test
	@Order(6)
	public void test06_excelReports()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnViewIssueButton();
		viewIssuePage.clickOnExcelReports();
	}
	
	// add issue
	// clone issue
	//Negative testing
	// submmit issue without fill out required fieldsLoginPage
	// attach tags without fill out Separate by field
	// send note without fill out Note field


}
