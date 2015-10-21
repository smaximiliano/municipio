package bd2.tp4.dao.impl;

import org.hibernate.Session;

import bd2.tp4.entity.Categoria;

public class CategoriaDAOImpl extends GenericDAOImpl<Categoria> {

	public CategoriaDAOImpl(Session session){
		super(session);
	}

}
