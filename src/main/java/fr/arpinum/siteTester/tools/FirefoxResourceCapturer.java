package fr.arpinum.siteTester.tools;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import fr.arpinum.siteTester.domain.Resource;

public class FirefoxResourceCapturer implements ResourceCapturer {

	@Override
	public byte[] capture(Resource resource) {
		driver.get(resource.fullPath());
		byte[] data = driver.getScreenshotAs(OutputType.BYTES);
		return data;
	}

	public void close() {
		driver.close();
	}

	private final FirefoxDriver driver = new FirefoxDriver();

}
