package bd2.tp4.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bd2.tp4.entity.Categoria;


public class Main {
	public static void main(String[] args) throws InterruptedException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tp4");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria("catJPA", 80000);
		
		em.persist(categoria);
		em.getTransaction().commit();
		
		em.close();
		entityManagerFactory.close();
		
/*
		Facade f = new FacadeImpl();

		f.nuevoCiudadano("maximiliano", "sosa", "37150835", "maxsos92@gmail.com");
		f.agregarProducto("productoASD", 50);
		f.nuevaCategoria("categoria ABC", 23000);

		List<CategoriaDTO> categorias = f.listarCategorias();
		List<CiudadanoDTO> ciudadanos = f.listarCiudadanos();
		List<ProductoDTO> productos = f.obtenerProductos();


		String idCiudadano = null;
		for (CiudadanoDTO ciu: ciudadanos ) {
			System.out.println("-----------CIUDADANO----------------");
			System.out.println(ciu.getId());
			System.out.println(ciu.getNombre());
			System.out.println(ciu.getPuntajeTotal());
			idCiudadano = ciu.getId();
		}

		String idCategoria = null;
		for(CategoriaDTO cat:categorias){
			System.out.println("-----------CATEGORIA----------------");
			System.out.println(cat.getId());
			System.out.println(cat.getNombre());
			System.out.println(cat.getPuntaje());
			idCategoria = cat.getId();
		}

		String idProducto = null;
		for(ProductoDTO pro:productos){
			System.out.println("-----------PRODUCTO----------------");
			System.out.println(pro.getId());
			System.out.println(pro.getDescripcion());
			System.out.println(pro.getPuntaje());
			idProducto = pro.getId();
		}

		f.nuevoReclamo(idCiudadano, idCategoria, new Date(System.currentTimeMillis()), "reclamo prueba666", "ba 15");
		List<ReclamoDTO> reclamos = f.traerReclamos();

		String idReclamo = null;
		for (ReclamoDTO reclamo : reclamos ) {
			System.out.println("--------------RECLAMO-----------");
			System.out.println(reclamo.getDescripcion());
			System.out.println(reclamo.getDireccion());
			idReclamo = reclamo.getId();
		}

		f.canjear(idCiudadano, idProducto);

		f.agregarEvento(idReclamo,"evento 1", new Date(System.currentTimeMillis()));

		List<CiudadanoDTO> resul = f.getCiudadanosQueCanjearon(idProducto, new Date(System.currentTimeMillis()));

		for (CiudadanoDTO cdto : resul) {
			System.out.println(cdto.getId());
			System.out.println(cdto.getNombre());
		}

		resul = f.getCiudadanosQueGeneraronReclamosConCategoria(idCategoria);

		for (CiudadanoDTO cdto : resul) {
			System.out.println(cdto.getId());
			System.out.println(cdto.getNombre());
		}

		productos = f.getProductosCanjeadosPorCiudadanosQueIniciaronReclamosEn(10, 2015);

		for(ProductoDTO pro:productos){
			System.out.println("**************PRODUCTO CONSULTA**************");
			System.out.println(pro.getId());
			System.out.println(pro.getDescripcion());
			System.out.println(pro.getPuntaje());
		}

		f.modificarCategoria(idCategoria, "Cat ASD", 98765);
		HibernateUtil.getSessionFactory().close();

*/
	}
}
