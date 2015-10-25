package bd2.tp4.dao;

import java.util.List;

public interface GenericDAO<T> {
	T findById(String id);

	List<T> findAll();

	T makePersistent(T entity);

	void makeTransient(T entity);

	void update(T entity);
}
