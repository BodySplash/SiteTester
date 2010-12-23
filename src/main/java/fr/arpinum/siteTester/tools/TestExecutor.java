package fr.arpinum.siteTester.tools;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.inject.Inject;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.SiteTest;

public class TestExecutor {

	@Inject
	public TestExecutor(ResourceCapturer resourceCapturer) {
		this.resourceCapturer = resourceCapturer;
	}

	public void schedule(final SiteTest test) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				test.run(resourceCapturer);
				Repositories.siteTests().update(test);
			}
		});
	}

	private Executor executor = Executors.newSingleThreadExecutor();
	private final ResourceCapturer resourceCapturer;
}
