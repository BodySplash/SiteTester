package fr.arpinum.siteTester.test;

import java.io.File;

import org.junit.rules.ExternalResource;

import fr.arpinum.siteTester.domain.Repositories;
import fr.arpinum.siteTester.tools.Database;

public class WithDatabase extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		dbFile = File.createTempFile("test_db40", "db");
		database = Database.open(dbFile);
		Repositories.setDatabase(database);
	}

	@Override
	protected void after() {
		database.close();
		dbFile.delete();
	}

	public void flush() {
		database.close();
		database.reopen();
	}

	public void commit() {
		database.commit();
	}

	public Database getInstance() {
		return database;
	}

	private File dbFile;
	private Database database;

}
