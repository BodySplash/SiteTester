package fr.arpinum.siteTester.web;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Repository;
import fr.arpinum.siteTester.domain.Site;

public class SitesResource extends ServerResource {

	@Get
	public Representation represent() {
		Repository<Site> repo = Repositories.sites();
		return SiteTesterTemplateRepresentation.forPath("/sites.ftl").with("sites", repo.getAll());
	}

	@Post
	public void add(Form form) {
		Site newSite = new Site(form.getFirstValue("url"));
		Repositories.sites().add(newSite);
	}
}
