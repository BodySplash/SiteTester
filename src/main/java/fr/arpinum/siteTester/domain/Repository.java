package fr.arpinum.siteTester.domain;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import fr.arpinum.siteTester.tools.Database;

public abstract class Repository<T> {

	public void add(T element) {
		Database.INSTANCE.store(element);
	}

	public List<T> getAll() {
		return Database.INSTANCE.getAll(getClassePersistante());
	}

	@SuppressWarnings("unchecked")
	protected final Class<T> getClassePersistante() {
		final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) superclass.getActualTypeArguments()[0];
	}
}