package tests;

import java.util.Properties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import core.DriverFactory;
import utils.ConfigFileReader;
import utils.Enums.Navegadores;

public class BaseTest {

	private static Properties prop = ConfigFileReader.getProp();

	@BeforeEach
	public void setUp() {
		
		DriverFactory.openBrowser(Navegadores.CHROME, prop.getProperty("url.base"));

	}
	

	@AfterEach
	public void tearDownTest() {

		DriverFactory.closeDriver();

	}

	@AfterAll
	public static void tearDown() {

		DriverFactory.killDriver();
	}

}
