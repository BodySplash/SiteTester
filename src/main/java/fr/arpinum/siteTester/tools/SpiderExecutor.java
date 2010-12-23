package fr.arpinum.siteTester.tools;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import fr.arpinum.siteTester.domain.Repositories;

public class SpiderExecutor {

	@Inject
	public SpiderExecutor(Database database) {
		this.database = database;
	}

	public List<Spider> spiders() {
		return spiders;
	}

	public void schedule(final Spider spider) {
		spiders.add(spider);
		executor.execute(new Runnable() {

			@Override
			public void run() {
				spider.crawl();
				Repositories.sites().update(spider.getSite());
				database.commit();
			}
		});
	}

	private List<Spider> spiders = Lists.newArrayList();
	private Executor executor = Executors.newSingleThreadExecutor();
	private final Database database;
}
