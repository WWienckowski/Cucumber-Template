package driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import io.cucumber.core.api.Scenario;

public final class DriverFactory {

	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

	// To quit the drivers and browsers at the end only.
	private static List<WebDriver> storedDrivers = new ArrayList<>();
	
	private static ThreadLocal<Scenario> scenario = new ThreadLocal<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				storedDrivers.forEach(WebDriver::quit);
			}
		});
	}

	private DriverFactory() {}

	public static WebDriver getDriver() {
		return drivers.get();
	}

	public static void addDriver(WebDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);
	}

	public static void removeDriver() {
		storedDrivers.remove(drivers.get());
		drivers.remove();
	}
	
	public static void setScenario(Scenario scen) {
		scenario.set(scen);
	}
	
	public static Scenario getScenario() {
		return scenario.get();
	}
 }
