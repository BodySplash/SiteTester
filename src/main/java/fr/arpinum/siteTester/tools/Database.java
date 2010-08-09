package fr.arpinum.siteTester.tools;

import java.io.File;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.google.common.collect.Lists;

import fr.arpinum.siteTester.domain.Site;

public class Database {

	public static Database open(File file) {
		Database result = new Database(file);
		result.reopen();
		return result;
	}

	private Database(File dbFile) {
		this.dbFile = dbFile;
	}

	public void store(final Object object) {
		container.store(object);
	}

	public <T> List<T> getAll(final Class<T> clazz) {
		return container.queryByExample(clazz);
	}

	public <T> List<T> getByPredicate(Predicate<T> predicate) {
		ObjectSet<T> result = container.query(predicate);
		return Lists.newArrayList(result);
	}

	public void close() {
		container.close();
	}

	public void reopen() {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		configuration.common().objectClass(Site.class).cascadeOnUpdate(true);
		container = Db4oEmbedded.openFile(configuration, dbFile.getAbsolutePath());
	}

	public void commit() {
		container.commit();
	}

	public void rollback() {
		container.rollback();
	}

	private ObjectContainer container;
	private File dbFile;

}
