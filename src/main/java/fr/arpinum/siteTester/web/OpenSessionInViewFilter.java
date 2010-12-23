package fr.arpinum.siteTester.web;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;

import fr.arpinum.siteTester.tools.Database;

public class OpenSessionInViewFilter extends Filter {

	public OpenSessionInViewFilter(Database database) {
		this.database = database;

	}

	@Override
	protected void afterHandle(Request request, Response response) {
		if (response.getStatus().isSuccess()) {
			database.commit();
		} else {
			database.rollback();
		}
	}

	private final Database database;
}
