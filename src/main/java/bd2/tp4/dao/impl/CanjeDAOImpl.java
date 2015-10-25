package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Canje;

public class CanjeDAOImpl extends GenericDAOImpl<Canje> {

	public CanjeDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
