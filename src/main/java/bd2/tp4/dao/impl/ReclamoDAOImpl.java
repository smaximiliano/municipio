package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Reclamo;

public class ReclamoDAOImpl extends GenericDAOImpl<Reclamo>{

	public ReclamoDAOImpl(EntityManager entityManager){
		super(entityManager);
	}

}
