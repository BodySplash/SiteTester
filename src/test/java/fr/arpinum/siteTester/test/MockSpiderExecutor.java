package fr.arpinum.siteTester.test;

import com.google.inject.Inject;

import fr.arpinum.siteTester.tools.Database;
import fr.arpinum.siteTester.tools.Spider;
import fr.arpinum.siteTester.tools.SpiderExecutor;

public class MockSpiderExecutor extends SpiderExecutor {

	public static Spider lastSpider;

	@Inject
	public MockSpiderExecutor(Database database) {
		super(database);
	}

	@Override
	public void schedule(Spider spider) {
		lastSpider = spider;
	}

}
