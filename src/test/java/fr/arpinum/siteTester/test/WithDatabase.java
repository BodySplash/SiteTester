package fr.arpinum.siteTester.test;

import java.io.File;

import org.junit.rules.ExternalResource;

import fr.arpinum.siteTester.tools.Database;

public class WithDatabase extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		dbFile = File.createTempFile("test_db40", "db");
		Database.INSTANCE.open(dbFile);
	}

	@Override
	protected void after() {
		Database.INSTANCE.close();
		dbFile.delete();
	}

	public void flush() {
		Database.INSTANCE.close();
		Database.INSTANCE.open(dbFile);
	}

	private File dbFile;

}
