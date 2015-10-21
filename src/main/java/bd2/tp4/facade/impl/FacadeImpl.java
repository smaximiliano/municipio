package bd2.tp4.facade.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

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
import bd2.tp4.dto.EventoDTO;
import bd2.tp4.dto.ProductoDTO;
import bd2.tp4.dto.ReclamoDTO;
import bd2.tp4.entity.Canje;
import bd2.tp4.entity.Categoria;
import bd2.tp4.entity.Ciudadano;
import bd2.tp4.entity.Evento;
import bd2.tp4.entity.Producto;
import bd2.tp4.entity.Reclamo;
import bd2.tp4.facade.Facade;
import bd2.tp4.utility.HibernateUtil;

public class FacadeImpl implements Facade {

	private GenericDAO<Categoria> categoriaDAO;
	private CiudadanoDAOImpl ciudadanoDAO;
	private ProductoDAOImpl productoDAO;
	private GenericDAO<Canje> canjeDAO;
	private GenericDAO<Reclamo> reclamoDAO;
	private GenericDAO<Evento> eventoDAO;
	private Session session;

	public void nuevaCategoria(String nombreCategoria, int puntaje) {
	try{
 		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		categoriaDAO = new CategoriaDAOImpl(session);
		Categoria categoria = new Categoria(nombreCategoria, puntaje);
		categoriaDAO.makePersistent(categoria);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	public void bajaCategoria(String id) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Categoria categoria = categoriaDAO.findById(id);
		categoriaDAO.makeTransient(categoria);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	public List<CategoriaDTO> listarCategorias(){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		categoriaDAO = new CategoriaDAOImpl(session);
		List<CategoriaDTO> categorias = CategoriaDTO.construir(categoriaDAO
				.findAll());
		session.getTransaction().commit();
		session.close();
		return categorias;
	}

	public void nuevoCiudadano(String nombre, String apellido, String dni,
			String email) {
	try{
		int puntajeTotal = 0;
		session =	 HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, email,
				puntajeTotal);
		ciudadanoDAO.makePersistent(ciudadano);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	public void agregarProducto(String descripcion, int puntaje) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		productoDAO = new ProductoDAOImpl(session);
		Producto producto = new Producto(descripcion, puntaje);
		productoDAO.makePersistent(producto);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	public void canjear(String idCiudadano, String idProducto) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		productoDAO = new ProductoDAOImpl(session);
		canjeDAO = new CanjeDAOImpl(session);
		Ciudadano ciudadano = ciudadanoDAO.findById(idCiudadano);
		Producto producto = productoDAO.findById(idProducto);
		ciudadano.canjear(producto);
		Canje canje = ciudadano.getUltimoCanje(producto);
		canjeDAO.makePersistent(canje);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	@Override
	public void nuevoReclamo(String idCiudadano, String idCategoria, Date fecha, String descripcion, String direccion) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		reclamoDAO = new ReclamoDAOImpl(session);
		Ciudadano ciudadano = ciudadanoDAO.findById(idCiudadano);
		Categoria categoria = categoriaDAO.findById(idCategoria);
		ciudadano.crearReclamo(fecha, descripcion, direccion,	categoria);
		reclamoDAO.makePersistent(ciudadano.getReclamo(fecha, descripcion, direccion));
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	@Override
	public void agregarEvento(String idReclamo, String descripcion, Date fecha) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		eventoDAO = new EventoDAOImpl(session);
		Reclamo reclamo = reclamoDAO.findById(idReclamo);
		reclamo.crearEvento(descripcion, fecha);
		Evento evento = reclamo.getEvento(descripcion, fecha);
		eventoDAO.makePersistent(evento);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	@Override
	public List<CiudadanoDTO> listarCiudadanos() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		List<CiudadanoDTO> all = CiudadanoDTO.construir(ciudadanoDAO.findAll());
		session.getTransaction().commit();
		session.close();
		return all;
	}

	@Override
	public List<ProductoDTO> obtenerProductos() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		productoDAO = new ProductoDAOImpl(session);
		List<ProductoDTO> all = ProductoDTO.construir(productoDAO.findAll());
		session.getTransaction().commit();
		session.close();
		return all;
	}

	@Override
	public List<ReclamoDTO> traerReclamos() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		reclamoDAO = new ReclamoDAOImpl(session);
		List<ReclamoDTO> all = ReclamoDTO.construir(reclamoDAO.findAll());
		session.getTransaction().commit();
		session.close();
		return all;
	}

	@Override
	public List<CanjeDTO> listarCanjes() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		canjeDAO = new CanjeDAOImpl(session);
		List<CanjeDTO> all = CanjeDTO.construir(canjeDAO.findAll());
		session.getTransaction().commit();
		session.close();
		return all;
	}

	public List<CiudadanoDTO> getCiudadanosQueCanjearon(String idProducto, Date fecha){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		productoDAO = new ProductoDAOImpl(session);
		List<Ciudadano> ciudadanos = ciudadanoDAO.getCiudadanosQueCanjearon(productoDAO.findById(idProducto), fecha);
		List<CiudadanoDTO> consulta = CiudadanoDTO.construir(ciudadanos);
		session.getTransaction().commit();
		session.close();
		return consulta;
	}

	public List<CiudadanoDTO> getCiudadanosQueGeneraronReclamosConCategoria(String idCategoria){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ciudadanoDAO = new CiudadanoDAOImpl(session);
		categoriaDAO = new CategoriaDAOImpl(session);
		List<Ciudadano> ciudadanos = ciudadanoDAO.getCiudadanosQueGeneraronReclamosConCategoria(categoriaDAO.findById(idCategoria));
		List<CiudadanoDTO> consulta = CiudadanoDTO.construir(ciudadanos);
		session.getTransaction().commit();
		session.close();
		return consulta;
	}

	public List<ProductoDTO> getProductosCanjeadosPorCiudadanosQueIniciaronReclamosEn(int month, int year){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		productoDAO = new ProductoDAOImpl(session);
		List<Producto> productos = productoDAO.getProductosCanjeadosPorCiudadanosQueIniciaronReclamosEn(month, year);
		List<ProductoDTO> consulta = ProductoDTO.construir(productos);
		session.getTransaction().commit();
		session.close();
		return consulta;
	}

	@Override
	public void modificarCategoria(String id, String nuevoNombreCategoria,
			int nuevoPuntaje){
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Categoria categoria = (Categoria)session.get(Categoria.class, id);
				if (categoria == null)
					throw(new RuntimeException("Categoria a actualizar no encontrada "+id));
				categoria.setNombre(nuevoNombreCategoria);
				categoria.setPuntaje(nuevoPuntaje);
				session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				session.close();
			}
	}

	@Override
	public void bajaCiudadano(String id) {
	try{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Ciudadano ciudadano = ciudadanoDAO.findById(id);
		ciudadanoDAO.makeTransient(ciudadano);
		session.getTransaction().commit();
	}catch(Exception e){
		session.getTransaction().rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	}

	@Override
	public void modificarCiudadano(String id, String nuevoNombre, String nuevoApellido, String nuevoDni, String nuevoEmail) {
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Ciudadano ciudadano = (Ciudadano)session.get(Ciudadano.class, id);
			if (ciudadano == null)
				throw(new RuntimeException("Ciudadano a actualizar no encontrado "+id));
			ciudadano.setNombre(nuevoNombre);
			ciudadano.setApellido(nuevoApellido);
			ciudadano.setDni(nuevoDni);
			ciudadano.setEmail(nuevoEmail);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}

	@Override
	public List<ReclamoDTO> traerReclamos(String idciudadano) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoDTO> traerEventos(String idReclamo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<CanjeDTO> listarCanjes(String idCiudadano) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bajaProducto(String idProducto) {
		// TODO Auto-generated method stub

	}
}
