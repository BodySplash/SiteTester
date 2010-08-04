package fr.arpinum.siteTester.domain;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.google.common.collect.Lists;

import fr.arpinum.siteTester.tools.Database;

public abstract class Repository<T> {

	public void add(T element) {
		Database.INSTANCE.store(element);
	}

	public List<T> getAll() {
		return Lists.newArrayList(Database.INSTANCE.getAll(getPersistentClass()));
	}

	@SuppressWarnings("unchecked")
	protected final Class<T> getPersistentClass() {
		final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) superclass.getActualTypeArguments()[0];
	}
}