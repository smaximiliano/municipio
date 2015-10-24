package bd2.tp4.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import bd2.tp4.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected EntityManager entityManager;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager entityManager) {
		persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.entityManager = entityManager;
	}

	public T findById(String id) {
		T entity = (T) entityManager.load(persistentClass, id);
		return entity;
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery( "from "+persistentClass.getName(), persistentClass).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = entityManager.createCriteria(persistentClass);
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	@Override
	public T makePersistent(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public void makeTransient(T entity) {
		entityManager.remove(entity);
	}

	public void flush() {
		entityManager.flush();
	}

	public void clear() {
		entityManager.clear();
	}


}
