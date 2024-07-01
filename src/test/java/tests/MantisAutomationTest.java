package tests;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import pages.ChangeLogPage;
import pages.HomePage;
import pages.IssueDetailsPage;
import pages.LoginPage;
import pages.RoadMapPage;
import pages.ViewIssuePage;
import utils.ConfigFileReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class MantisAutomationTest extends BaseTest {
	
	private static Properties prop = ConfigFileReader.getProp();
	public final String username = prop.getProperty("username");
	public final String password = prop.getProperty("password");
	private LoginPage loginPage = new LoginPage();
	private HomePage homePage = new HomePage();
	private IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
	private ViewIssuePage viewIssuePage = new ViewIssuePage();
	private ChangeLogPage changeLogPage = new ChangeLogPage();
	private RoadMapPage roadMapPage = new RoadMapPage();

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
		Assertions.assertTrue(loginPage.waitHomePageLoaded());


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
		
		Assertions.assertEquals("View Issue Details", viewIssuePage.getTittleViewIssueDetails());
		
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
	
	@Test
	@Order(7)
	public void test07_invalidLogin() {
		
		// Fill usernameField
		loginPage.enterUsername("invalidLogin");
		
		// Click on Login Button
		loginPage.clickOnLoginBtn();
		

		loginPage.enterPassword(password);
		
		// Click on Login Button
		loginPage.clickOnLoginBtn();
		
		Assertions.assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", loginPage.getLoginMsgError());
		
		
	}
	
	@Test
	@Order(8)
	public void test08_changeLog()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnChangeLogButton();
		Assertions.assertEquals("No Change Log information available", changeLogPage.getInformationPage());
		
	}
	
	@Test
	@Order(9)
	public void test09_roadmap()throws InterruptedException {
		
		test01_loginSucess();
		homePage.clickOnRoadmapButton();
		Assertions.assertEquals("No Roadmap information available", roadMapPage.getInformationPage());
		
	}
	

}
