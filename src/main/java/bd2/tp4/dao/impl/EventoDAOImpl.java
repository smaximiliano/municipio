package bd2.tp4.dao.impl;

import org.hibernate.Session;

import bd2.tp4.entity.Evento;

public class EventoDAOImpl extends GenericDAOImpl<Evento>{

	public EventoDAOImpl(Session session){
		super(session);
	}
}
