package fr.arpinum.siteTester.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.domain.Site;

public abstract class WithSiteResource extends ServerResource {

	@Override
	protected void doInit() throws ResourceException {
		try {
			site = Repositories.sites().getByName(
					URLDecoder.decode(getRequestAttributes().get("uri").toString(), "UTF8"));
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Site getSite() {
		return site;
	}

	private Site site;
}