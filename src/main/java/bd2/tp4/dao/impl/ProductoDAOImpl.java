package bd2.tp4.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bd2.tp4.entity.Canje;
import bd2.tp4.entity.Ciudadano;
import bd2.tp4.entity.Producto;

public class ProductoDAOImpl extends GenericDAOImpl<Producto>{

	public ProductoDAOImpl(Session session) {
		super(session);
	}

	@Override
	public List<Producto> findByCriteria(Criterion... criterion){
		return super.findByCriteria();
	}
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosCanjeadosPorCiudadanosQueIniciaronReclamosEn(int month, int year){
		java.sql.Date minDate = parsearFecha("1/"+month+"/"+year);
		month = (month == 12) ? 1: month + 1;
		java.sql.Date maxDate = parsearFecha("1/"+month+"/"+year);

		Criteria criteria = session.createCriteria(Ciudadano.class);
		criteria.createCriteria("reclamosRealizados").add(Restrictions.between("fecha", minDate, maxDate));
		List<Ciudadano> ciudadanos = criteria.list();
		List<Producto> productos = new ArrayList<Producto>();
		for (Ciudadano c : ciudadanos)
			for (Canje canje : c.getCanjes())
					productos.add(canje.getProducto());
		return productos;
	}

	private java.sql.Date parsearFecha(String fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = null;
		try {
	 	d = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}

}
