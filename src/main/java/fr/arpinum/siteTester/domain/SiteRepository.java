package fr.arpinum.siteTester.domain;

import java.util.List;

import com.db4o.query.Predicate;

import fr.arpinum.siteTester.tools.Database;

public class SiteRepository extends Repository<Site> {

	SiteRepository() {
	}

	public Site getByName(final String name) {
		List<Site> sites = Database.INSTANCE.getByPredicate(new Predicate<Site>() {

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
