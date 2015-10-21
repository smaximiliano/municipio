package bd2.tp4.dao;

import java.util.List;

public interface GenericDAO<T> {
	 T findById(String id);

	    List<T> findAll();

	    List<T> findByExample(T exampleInstance, String[] excludeProperty);

	    T makePersistent(T entity);

	    void makeTransient(T entity);
}
