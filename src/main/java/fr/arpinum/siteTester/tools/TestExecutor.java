package fr.arpinum.siteTester.tools;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.SiteTest;

public class TestExecutor {

	public void schedule(final SiteTest test) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				test.run();
				Repositories.siteTests().update(test);
			}
		});
	}

	private Executor executor = Executors.newSingleThreadExecutor();
}
