package fr.arpinum.siteTester.tools;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;

public class SpiderExecutor {

	public List<Spider> spiders() {
		return spiders;
	}

	public void schedule(final Spider spider) {
		spiders.add(spider);
		executor.execute(new Runnable() {

			@Override
			public void run() {
				spider.crawl();
				Database.INSTANCE.commit();
			}
		});
	}

	private List<Spider> spiders = Lists.newArrayList();
	private Executor executor = Executors.newSingleThreadExecutor();
}