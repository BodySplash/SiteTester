package fr.arpinum.siteTester.web;

import fr.arpinum.siteTester.domain.SiteTest;
import fr.arpinum.siteTester.tools.TestExecutor;

public class MockTestExecutor extends TestExecutor {

	public MockTestExecutor() {
		super(null);
	}

	@Override
	public void schedule(SiteTest test) {
		this.testScheduled = test;
	}

	public SiteTest testScheduled;

}
