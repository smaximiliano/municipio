package bd2.tp4.dao.impl;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Producto;

public class ProductoDAOImpl extends GenericDAOImpl<Producto>{

	public ProductoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

/*
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
*/
}
