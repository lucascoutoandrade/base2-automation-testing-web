package core;

import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.OperatingSystem;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigFileReader;

public class DriverFactory {

	private static WebDriver driver;
	private static Enum nomeNavegador;
	public static Logger log = Logger.getLogger("QALogger");
	private static Properties prop = ConfigFileReader.getProp();
	private static String osName = System.getProperty("os.name").toLowerCase();
	private static String driverName;

	public static void openBrowser(Enum navegador, String URL) {
		nomeNavegador = navegador;
		getDriver().get(URL);

	}

	public static WebDriver getDriver() {
		if (driver == null) {
			// start driver
			createDriver();
		}
		return driver;

	}

	private static void createDriver() {

		/**
		 * select Browser
		 */

		switch (setBrowser()) {

		case "CHROME":
			try {

				setOSWebDriverSetup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				chromeOptions.addArguments("--start-maximized");
				driver = new ChromeDriver(chromeOptions);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				log.info("Selected Chrome Driver");
			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}
			break;

		case "HEADLESS":

			try {
				ChromeOptions chromeOptionsHeadless = new ChromeOptions();
				chromeOptionsHeadless.addArguments("--remote-allow-origins=*");
				chromeOptionsHeadless.addArguments("--headless");
				chromeOptionsHeadless.addArguments("disable-gpu");
				chromeOptionsHeadless.addArguments("window-size=1280x1024");
				driver = new ChromeDriver(chromeOptionsHeadless);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				log.info("Selected Chrome Headless Driver");
			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}
			break;

		case "FIREFOX":
			try {
				setOSWebDriverSetup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				log.info("Selected Gecko Driver");
			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}

			break;

		case "INTERNET_EXPLORER":
			try {
				setOSWebDriverSetup();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				DesiredCapabilities capabilitiesIE = new DesiredCapabilities();
				capabilitiesIE.setCapability("requireWindowFocus", true);
				capabilitiesIE.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				capabilitiesIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capabilitiesIE.setCapability("ie.ensureCleanSession", true);
				capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilitiesIE.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
				log.info("Selected Internet Explorer Driver");

			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}

		default:
			log.warning(">>>>>>> Warning browser not available! <<<<<<<<<<");
		}
	}

	/**
	 * Way to OS
	 */

	public static void setOSWebDriverSetup() {

		// Operating system is based on Linux/Unix/*AIX
		if (osName.contains("linux") || osName.contains("unix") || osName.contains("aix")) {

			setSetupDriver();
			// Operating system is Apple OSX based
		} else if (osName.contains("mac") || osName.contains("osx")) {

			setSetupDriver();
			// Operating system is based on Windows
		} else {

			setSetupDriver();
		}

	}

	/**
	 * Setup driver
	 */

	public static void setSetupDriver() {

		switch (driverName.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().operatingSystem(OperatingSystem.LINUX).setup();
			break;
		case "ie":
			WebDriverManager.iedriver().operatingSystem(OperatingSystem.LINUX).setup();
			break;

		default:
			log.warning(">>>>>>> Warning browser not available! <<<<<<<<<<");

		}

	}

	/**
	 * Define browser
	 */
	public static String setBrowser() {

		driverName = prop.getProperty("browser");

		if (driverName != null) {
			return prop.getProperty("browser");
		} else {
			return nomeNavegador.name();
		}

	}

	/**
	 * finish driver
	 */
	public static void killDriver() {

		if (driver != null) {
			driver.quit();
			driver = null;

		}
	}

	/**
	 * close driver
	 */

	public static void closeDriver() {

		if (driver != null) {
			driver.close();
			driver = null;

		}
	}

}
