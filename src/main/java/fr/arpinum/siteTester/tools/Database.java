package fr.arpinum.siteTester.tools;

import java.io.File;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.google.common.collect.Lists;

public enum Database {

	INSTANCE;

	private Database() {
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

	public void commit() {
		container.commit();
	}

	public void rollback() {
		container.rollback();
	}

	public void open(File file) {
		container = Db4oEmbedded.openFile(file.getAbsolutePath());
	}

	private ObjectContainer container;

}
