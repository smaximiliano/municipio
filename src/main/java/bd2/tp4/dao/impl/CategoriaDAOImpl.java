package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Categoria;

public class CategoriaDAOImpl extends GenericDAOImpl<Categoria> {

	public CategoriaDAOImpl(EntityManager entityManager){
		super(entityManager);
	}

}
