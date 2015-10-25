package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Ciudadano;

public class CiudadanoDAOImpl extends GenericDAOImpl<Ciudadano> {

	public CiudadanoDAOImpl(EntityManager entityManager){
		super(entityManager);
	}


}
