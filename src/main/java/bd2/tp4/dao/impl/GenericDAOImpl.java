package bd2.tp4.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

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
		T entity = (T) entityManager.find(persistentClass, id);
		return entity;
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery( "from "+persistentClass.getName(), persistentClass).getResultList();
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

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
		
	}

	


}
