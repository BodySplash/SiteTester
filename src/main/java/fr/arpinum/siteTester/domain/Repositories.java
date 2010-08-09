package fr.arpinum.siteTester.domain;

import fr.arpinum.siteTester.tools.Database;

public class Repositories {

	public static SiteRepository sites() {
		return new SiteRepository(database);
	}

	public static SiteTestRepository siteTests() {
		return new SiteTestRepository(database);
	}

	public static void setDatabase(Database database) {
		Repositories.database = database;

	}

	private static Database database;
}
