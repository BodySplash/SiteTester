package fr.arpinum.siteTester.domain;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.google.common.collect.Lists;

import fr.arpinum.siteTester.tools.Database;

public abstract class Repository<T> {

	public Repository(Database database) {
		this.database = database;

	}

	public void add(T element) {
		database.store(element);
	}

	public List<T> getAll() {
		return Lists.newArrayList(database.getAll(getPersistentClass()));
	}

	@SuppressWarnings("unchecked")
	protected final Class<T> getPersistentClass() {
		final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) superclass.getActualTypeArguments()[0];
	}

	public void update(Site site) {
		database.store(site);
	}

	protected Database getDatebase() {
		return database;
	}

	private final Database database;
}