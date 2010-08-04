package fr.arpinum.siteTester.web;

import org.restlet.Context;
import org.restlet.data.Reference;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class SiteTesterRouter extends Router {

	public SiteTesterRouter(Context context) {
		super(context);
		attachResources();
	}

	void attachResources() {
		attach("/", WelcomeResource.class);
		attach("/js", new Directory(getContext(), new Reference("clap://class/static-content/js")));
		attach("/sites", SitesResource.class);
		attach("/sites/{uri}/spiders", SpidersResource.class);
		attach("/theme", new Directory(getContext(), new Reference("clap://class/static-content/theme")));
	}

}
