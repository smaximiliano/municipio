package bd2.tp4.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd2.tp4.entity.Categoria;
import bd2.tp4.entity.Ciudadano;
import bd2.tp4.entity.Producto;

public class CiudadanoDAOImpl extends GenericDAOImpl<Ciudadano> {

	public CiudadanoDAOImpl(Session session){
		super(session);
	}

	@SuppressWarnings("unchecked")
	public List<Ciudadano> getCiudadanosQueCanjearon(Producto producto, Date dia){
		Criteria criteria = entityManager.createCriteria(Ciudadano.class);
		criteria.createCriteria("canjes").add(Restrictions.eq("producto", producto)).add(Restrictions.eq("fecha", dia));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<Ciudadano> getCiudadanosQueGeneraronReclamosConCategoria(Categoria categoria){
		Criteria criteria = entityManager.createCriteria(Ciudadano.class);
		criteria.createCriteria("reclamosRealizados").add(Restrictions.eq("categoria", categoria));
		return criteria.list();
	}

}
