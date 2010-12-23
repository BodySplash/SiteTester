package fr.arpinum.siteTester.web;

import org.restlet.Context;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class SiteTesterRouter extends Router {

	public SiteTesterRouter(final Context context, final FinderFactory finderFactory) {
		super(context);
		this.finderFactory = finderFactory;
		attachResources();
	}

	void attachResources() {
		attach("/", WelcomeResource.class);
		attach("/js", new Directory(getContext(), new Reference("clap://class/static-content/js")));
		attach("/sites", finderFactory.finderOf(SitesResource.class));
		attach("/sites/{uri}", finderFactory.finderOf(SiteResource.class));
		attach("/sites/{uri}/resources", finderFactory.finderOf(ResourcesResource.class));
		attach("/sites/{uri}/spiders", finderFactory.finderOf(SpidersResource.class));
		attach("/sites/{uri}/tests", finderFactory.finderOf(SiteTestsResource.class));
		attach("/theme", new Directory(getContext(), new Reference("clap://class/static-content/theme")));
	}

	private final FinderFactory finderFactory;
}
