package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import driver.DriverFactory;

public class SharedDriver {

	public SharedDriver() {
		if (DriverFactory.getDriver() == null) {
			try{
				String selenium = System.getProperty("selenium");
				selenium = selenium==null ? "http://localhost:4444/wd/hub" : selenium;
				WebDriver driver = new RemoteWebDriver(new URL(selenium), new ChromeOptions());
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				DriverFactory.addDriver(driver);
			} catch(MalformedURLException e){
				System.out.println("Error"+e);
			}
		}
	}
}
