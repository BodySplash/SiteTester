package fr.arpinum.siteTester.domain;

import java.util.List;

import com.db4o.query.Predicate;

import fr.arpinum.siteTester.tools.Database;

public class SiteRepository extends Repository<Site> {

	public SiteRepository(Database database) {
		super(database);
	}

	public Site getByName(final String name) {
		List<Site> sites = getDatebase().getByPredicate(new Predicate<Site>() {

			@Override
			public boolean match(Site site) {
				return site.getName().equals(name);
			}

		});
		if (!sites.isEmpty()) {
			return sites.get(0);
		}
		return null;
	}
}
