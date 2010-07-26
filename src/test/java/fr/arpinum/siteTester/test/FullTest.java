package fr.arpinum.siteTester.test;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import fr.arpinum.siteTester.domain.Capture;
import fr.arpinum.siteTester.domain.Site;
import fr.arpinum.siteTester.domain.SiteTest;
import fr.arpinum.siteTester.tools.FirefoxResourceCapturer;
import fr.arpinum.siteTester.tools.Spider;

public class FullTest {

	@Test
	@Ignore
	public void test() {
		Site blog = new Site("http://www.arpinum.fr");
		Spider spider = new Spider(blog);
		spider.crawl();

		FirefoxResourceCapturer resourceCapturer = new FirefoxResourceCapturer();
		SiteTest test = new SiteTest(blog);
		test.setResourceCapturer(resourceCapturer);
		test.run();
		resourceCapturer.close();
		for (Capture capture : test.captures()) {
			capture.exportToFile(new File("/Users/jb/tmp/blog/" + capture.getPath() + ".png"));
		}
	}

}
