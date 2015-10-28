package bd2.tp4.facade;

import java.sql.Date;
import java.util.List;

import bd2.tp4.dto.CanjeDTO;
import bd2.tp4.dto.CategoriaDTO;
import bd2.tp4.dto.CiudadanoDTO;
import bd2.tp4.dto.ProductoDTO;
import bd2.tp4.dto.ReclamoDTO;

public interface Facade {
	public void nuevaCategoria(String nombreCategoria, int puntaje);

	public void bajaCategoria(String id);

	public void modificarCategoria(String id, String nuevoNombreCategoria,
			int nuevoPuntaje);

	public List<CategoriaDTO> listarCategorias();

	public void nuevoCiudadano(String nombre, String apellido, String dni,
			String email);

	public void bajaCiudadano(String id);

	public void modificarCiudadano(String id, String nombre, String apellido, String dni, String email);

	public List<CiudadanoDTO> listarCiudadanos();

	public void nuevoReclamo(String idCiudadano, String idCategoria, Date fecha, String descripcion, String direccion);

	public List<ReclamoDTO> traerReclamos();

	void agregarEvento(String idReclamo, String descripcion, Date fecha);

	void canjear(String idCiudadano, String idProducto);

	List<CanjeDTO> listarCanjes();

	void agregarProducto(String descripcion, int puntaje);

	void bajaProducto(String idProducto);

	List<ProductoDTO> obtenerProductos();

}