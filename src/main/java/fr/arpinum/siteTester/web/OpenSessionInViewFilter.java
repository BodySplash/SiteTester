package fr.arpinum.siteTester.web;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;

import fr.arpinum.siteTester.tools.Database;

public class OpenSessionInViewFilter extends Filter {

	@Override
	protected void afterHandle(Request request, Response response) {
		if (response.getStatus().isSuccess()) {
			Database.INSTANCE.commit();
		} else {
			Database.INSTANCE.rollback();
		}
	}
}
