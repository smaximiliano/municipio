package bd2.tp4.dao.impl;

import org.hibernate.Session;

import bd2.tp4.entity.Reclamo;

public class ReclamoDAOImpl extends GenericDAOImpl<Reclamo>{

	public ReclamoDAOImpl(Session session){
		super(session);
	}

}
