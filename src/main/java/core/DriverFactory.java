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
				WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				chromeOptions.addArguments("--start-maximized");
				driver = new ChromeDriver(chromeOptions);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				log.info("Chrome Driver selecionado");
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
				log.info("Chrome Headless Driver selecionado");
			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}
			break;
			
		case "FIREFOX":
			try {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				log.info("Gecko Driver selecionado");
			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}

			break;
			
		case "INTERNET_EXPLORER":
			try {
				WebDriverManager.iedriver().setup();
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
				log.info("Internet Explorer Driver selecionado");

			} catch (Throwable e) {
				log.info("ERRO: " + e.getMessage());
			}

		default:
			log.warning(">>>>>>> ATENÇÃO SEU NAVEGADOR NÃO ESTÁ DISPONÍVEL! <<<<<<<<<<");
		}
	}

	/**
	 * Define browser
	 */
	public static String setBrowser() {
		
		if (prop.getProperty("browser") != null) {
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
