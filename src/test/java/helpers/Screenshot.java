package helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import driver.DriverFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Screenshot {
	
	public static void includeScreenshot() {
		DriverFactory.getScenario().write("Including a screenshot with this step for manual review");
		byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
		DriverFactory.getScenario().embed(screenshot, "image/png");
	}

	public static void includeScreenshotOfElement(WebElement element) {
		BufferedImage screenshot = new AShot()
	            .shootingStrategy(ShootingStrategies.viewportPasting(100))
	            .takeScreenshot(DriverFactory.getDriver()).getImage();

	File screenshotFile = new File("target/image.png");
	try {
	    ImageIO.write(screenshot, "png", screenshotFile);
	} catch (Exception e) {
	    DriverFactory.getScenario().write("Screenshot failed");
	}

        try {
        	
			
			BufferedImage  fullScreen = ImageIO.read(screenshotFile);
			Point location= element.getLocation();
		    int width= element.getSize().getWidth();
		    int height= element.getSize().getHeight();
		    
		    BufferedImage elementImage= fullScreen.getSubimage(location.getX(), location.getY(),
		            width, height);
		    
		    ImageIO.write(elementImage, "png", screenshotFile);
		    byte[] screenByte = Files.readAllBytes(screenshotFile.toPath());
		    
		    DriverFactory.getScenario().embed(screenByte, "image/png");
		} catch (Exception e) {
			DriverFactory.getScenario().write("Screenshot failed");
		}
	    
	    
	}
}
