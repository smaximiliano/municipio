package bd2.tp4.facade.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import bd2.tp4.dao.GenericDAO;
import bd2.tp4.dao.impl.CanjeDAOImpl;
import bd2.tp4.dao.impl.CategoriaDAOImpl;
import bd2.tp4.dao.impl.CiudadanoDAOImpl;
import bd2.tp4.dao.impl.EventoDAOImpl;
import bd2.tp4.dao.impl.ProductoDAOImpl;
import bd2.tp4.dao.impl.ReclamoDAOImpl;
import bd2.tp4.dto.CanjeDTO;
import bd2.tp4.dto.CategoriaDTO;
import bd2.tp4.dto.CiudadanoDTO;
import bd2.tp4.dto.ProductoDTO;
import bd2.tp4.dto.ReclamoDTO;
import bd2.tp4.entity.Canje;
import bd2.tp4.entity.Categoria;
import bd2.tp4.entity.Ciudadano;
import bd2.tp4.entity.Evento;
import bd2.tp4.entity.Municipio;
import bd2.tp4.entity.Producto;
import bd2.tp4.entity.Reclamo;
import bd2.tp4.facade.Facade;
import bd2.tp4.utility.HibernateUtil;

public class FacadeImpl implements Facade {

	private static final String MUNICIPIO_ID = "1";
	private GenericDAO<Categoria> categoriaDAO;
	private GenericDAO<Ciudadano> ciudadanoDAO;
	private GenericDAO<Producto> productoDAO;
	private GenericDAO<Canje> canjeDAO;
	private GenericDAO<Reclamo> reclamoDAO;
	private GenericDAO<Evento> eventoDAO;
	private EntityManager entityManager;

	public void nuevaCategoria(String nombreCategoria, int puntaje) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			categoriaDAO = new CategoriaDAOImpl(entityManager);
			Categoria categoria = new Categoria(nombreCategoria, puntaje);
			categoriaDAO.makePersistent(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void bajaCategoria(String id) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			Categoria categoria = categoriaDAO.findById(id);
			categoriaDAO.makeTransient(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public List<CategoriaDTO> listarCategorias() {
		entityManager = HibernateUtil.getEntityManagerFactory()
				.createEntityManager();
		entityManager.getTransaction().begin();
		categoriaDAO = new CategoriaDAOImpl(entityManager);
		List<CategoriaDTO> categorias = CategoriaDTO.construir(categoriaDAO
				.findAll());
		entityManager.getTransaction().commit();
		entityManager.close();
		return categorias;
	}

	public void nuevoCiudadano(String nombre, String apellido, String dni,
			String email) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			Municipio m = entityManager.find(Municipio.class, MUNICIPIO_ID);
			ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
			Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, email);
			ciudadanoDAO.makePersistent(ciudadano);
			m.addCiudadano(ciudadano);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void agregarProducto(String descripcion, int puntaje) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			productoDAO = new ProductoDAOImpl(entityManager);
			Producto producto = new Producto(descripcion, puntaje);
			productoDAO.makePersistent(producto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void canjear(String idCiudadano, String idProducto) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
			productoDAO = new ProductoDAOImpl(entityManager);
			canjeDAO = new CanjeDAOImpl(entityManager);
			Ciudadano ciudadano = ciudadanoDAO.findById(idCiudadano);
			Producto producto = productoDAO.findById(idProducto);
			ciudadano.canjear(producto);
			Canje canje = ciudadano.getUltimoCanje(producto);
			canjeDAO.makePersistent(canje);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void nuevoReclamo(String idCiudadano, String idCategoria,
			Date fecha, String descripcion, String direccion) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
			reclamoDAO = new ReclamoDAOImpl(entityManager);
			categoriaDAO = new CategoriaDAOImpl(entityManager);
			Ciudadano ciudadano = ciudadanoDAO.findById(idCiudadano);
			Categoria categoria = categoriaDAO.findById(idCategoria);
			ciudadano.crearReclamo(fecha, descripcion, direccion, categoria);
			reclamoDAO.makePersistent(ciudadano.getReclamo(fecha, descripcion,
					direccion));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void agregarEvento(String idReclamo, String descripcion, Date fecha) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			eventoDAO = new EventoDAOImpl(entityManager);
			reclamoDAO = new ReclamoDAOImpl(entityManager);
			Reclamo reclamo = reclamoDAO.findById(idReclamo);
			reclamo.crearEvento(descripcion, fecha);
			Evento evento = reclamo.getEvento(descripcion, fecha);
			eventoDAO.makePersistent(evento);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<CiudadanoDTO> listarCiudadanos() {
		entityManager = HibernateUtil.getEntityManagerFactory()
				.createEntityManager();
		entityManager.getTransaction().begin();
		ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
		List<CiudadanoDTO> all = CiudadanoDTO.construir(ciudadanoDAO.findAll());
		entityManager.getTransaction().commit();
		entityManager.close();
		return all;
	}

	@Override
	public List<ProductoDTO> obtenerProductos() {
		entityManager = HibernateUtil.getEntityManagerFactory()
				.createEntityManager();
		entityManager.getTransaction().begin();
		productoDAO = new ProductoDAOImpl(entityManager);
		List<ProductoDTO> all = ProductoDTO.construir(productoDAO.findAll());
		entityManager.getTransaction().commit();
		entityManager.close();
		return all;
	}

	@Override
	public List<ReclamoDTO> traerReclamos() {
		entityManager = HibernateUtil.getEntityManagerFactory()
				.createEntityManager();
		entityManager.getTransaction().begin();
		reclamoDAO = new ReclamoDAOImpl(entityManager);
		List<ReclamoDTO> all = ReclamoDTO.construir(reclamoDAO.findAll());
		entityManager.getTransaction().commit();
		entityManager.close();
		return all;
	}

	@Override
	public List<CanjeDTO> listarCanjes() {
		entityManager = HibernateUtil.getEntityManagerFactory()
				.createEntityManager();
		entityManager.getTransaction().begin();
		canjeDAO = new CanjeDAOImpl(entityManager);
		List<CanjeDTO> all = CanjeDTO.construir(canjeDAO.findAll());
		entityManager.getTransaction().commit();
		entityManager.close();
		return all;
	}

	@Override
	public void modificarCategoria(String id, String nuevoNombreCategoria,
			int nuevoPuntaje) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			categoriaDAO = new CategoriaDAOImpl(entityManager);
			Categoria categoria = (Categoria) categoriaDAO.findById(id);
			if (categoria == null)
				throw (new RuntimeException(
						"Categoria a actualizar no encontrada " + id));
			categoria.setNombre(nuevoNombreCategoria);
			categoria.setPuntaje(nuevoPuntaje);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void bajaCiudadano(String id) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
			Municipio m = entityManager.find(Municipio.class, MUNICIPIO_ID);
			Ciudadano ciudadano = null;
			for (Ciudadano c : m.getCiudadanos())
				if (c.getId().equals(id)) {
					ciudadano = c;
					break;
				}
			m.removeCiudadano(ciudadano);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void modificarCiudadano(String id, String nuevoNombre,
			String nuevoApellido, String nuevoDni, String nuevoEmail) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			ciudadanoDAO = new CiudadanoDAOImpl(entityManager);
			Ciudadano ciudadano = (Ciudadano) ciudadanoDAO.findById(id);
			if (ciudadano == null)
				throw (new RuntimeException(
						"Ciudadano a actualizar no encontrado " + id));
			ciudadano.setNombre(nuevoNombre);
			ciudadano.setApellido(nuevoApellido);
			ciudadano.setDni(nuevoDni);
			ciudadano.setEmail(nuevoEmail);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void bajaProducto(String idProducto) {
		try {
			entityManager = HibernateUtil.getEntityManagerFactory()
					.createEntityManager();
			entityManager.getTransaction().begin();
			productoDAO = new ProductoDAOImpl(entityManager);
			Producto producto = productoDAO.findById(idProducto);
			if (producto == null)
				throw (new RuntimeException(
						"Producto a eliminar no encontrado " + idProducto));
			productoDAO.makeTransient(producto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
}
