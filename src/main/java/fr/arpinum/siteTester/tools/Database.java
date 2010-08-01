package fr.arpinum.siteTester.tools;

import java.io.File;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public enum Database {

	INSTANCE;

	private Database() {
		open();
	}

	public void store(final Object object) {
		container.store(object);
	}

	public <T> List<T> getAll(final Class<T> clazz) {
		return container.queryByExample(clazz);
	}

	public void closeAndOpen() {
		container.close();
		open();
	}

	public void flush() {
		container.close();
		dbFile.delete();
		open();
	}

	public void setFile(File dbFile) {
		this.dbFile = dbFile;
		closeAndOpen();

	}

	private void open() {
		container = Db4o.openFile(dbFile.getAbsolutePath());
	}

	private ObjectContainer container;
	private File dbFile = new File("db.db4o");

}
