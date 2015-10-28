package bd2.tp4.utility;

import javax.persistence.EntityManager;

import bd2.tp4.entity.Municipio;

public class CrearMunicipio {
	public static void main(String[] args){
		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Municipio m = new Municipio();
		em.persist(m);
		em.getTransaction().commit();
		em.close();
		HibernateUtil.getEntityManagerFactory().close();
	}
}
