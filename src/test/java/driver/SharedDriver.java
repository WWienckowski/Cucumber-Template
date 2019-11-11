package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SharedDriver {

	public SharedDriver() {
		if (DriverFactory.getDriver() == null) {
			try{
				String selenium = System.getProperty("selenium");
				selenium = selenium==null ? "http://localhost:4444/wd/hub" : selenium;
				WebDriver driver = new RemoteWebDriver(new URL(selenium), new ChromeOptions());
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				DriverFactory.addDriver(driver);
			} catch(MalformedURLException e){
				System.out.println("Error"+e);
			}
		}
	}
}
