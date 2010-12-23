package fr.arpinum.siteTester.domain;

import fr.arpinum.siteTester.tools.Database;

public class SiteTestRepository extends Repository<SiteTest> {

	public SiteTestRepository(Database database) {
		super(database);
	}

}
