package bd2.tp4.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import bd2.tp4.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected Session session;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl(Session session) {
		persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.session = session;
	}

	public T findById(String id) {
		T entity = (T) session.load(persistentClass, id);
		return entity;
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = session.createCriteria(persistentClass);
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	@Override
	public T makePersistent(T entity) {
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void makeTransient(T entity) {
		session.delete(entity);
	}

	public void flush() {
		session.flush();
	}

	public void clear() {
		session.clear();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = session.createCriteria(persistentClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

}
