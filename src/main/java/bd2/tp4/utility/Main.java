package bd2.tp4.utility;

import java.sql.Date;
import java.util.List;

import bd2.tp4.dto.CategoriaDTO;
import bd2.tp4.dto.CiudadanoDTO;
import bd2.tp4.dto.ProductoDTO;
import bd2.tp4.dto.ReclamoDTO;
import bd2.tp4.facade.Facade;
import bd2.tp4.facade.impl.FacadeImpl;


public class Main {
	public static void main(String[] args){
		
		Facade f = new FacadeImpl();
		
		f.nuevoCiudadano("maximiliano", "sosa", "37150835", "maxsos92@gmail.com");
		f.nuevoCiudadano("flaquito", "aveggio", "33123321", "jpaveggio@gmail.com");
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

		f.modificarCategoria(idCategoria, "Cat ASD", 98765);
		
		f.bajaCiudadano(idCiudadano);
		HibernateUtil.getEntityManagerFactory().close();

	}
}
