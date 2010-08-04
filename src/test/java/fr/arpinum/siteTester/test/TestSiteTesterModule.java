package fr.arpinum.siteTester.test;

import fr.arpinum.siteTester.web.SiteTesterModule;

public class TestSiteTesterModule extends SiteTesterModule {

	public TestSiteTesterModule(int port) {
		super(port);
	}

	@Override
	protected String getDatabaseFilePath() {
		return "dbtest.db4o";
	}

}
