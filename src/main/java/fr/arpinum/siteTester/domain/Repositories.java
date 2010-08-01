package fr.arpinum.siteTester.domain;

public class Repositories {

	public static SiteRepository sites() {
		return new SiteRepository();
	}

	public static SiteTestRepository siteTests() {
		return new SiteTestRepository();
	}

}
