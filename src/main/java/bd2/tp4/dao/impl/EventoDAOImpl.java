package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Evento;

public class EventoDAOImpl extends GenericDAOImpl<Evento>{

	public EventoDAOImpl(EntityManager entityManager){
		super(entityManager);
	}
}
