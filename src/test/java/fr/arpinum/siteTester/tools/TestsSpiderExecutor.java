package fr.arpinum.siteTester.tools;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class TestsSpiderExecutor {

	@Test
	public void canRun() throws InterruptedException {
		SpiderExecutor executor = new SpiderExecutor();
		Spider spider = mock(Spider.class);

		executor.schedule(spider);
		Thread.sleep(100);

		verify(spider).crawl();
	}
}
