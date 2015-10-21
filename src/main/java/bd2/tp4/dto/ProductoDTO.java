package bd2.tp4.dto;

import java.util.ArrayList;
import java.util.List;

import bd2.tp4.entity.Producto;

public class ProductoDTO {
	private String id;
	private String descripcion;
	private int puntaje;

	public ProductoDTO(Producto producto) {
		id = producto.getId();
		descripcion = producto.getDescripcion();
		puntaje = producto.getPuntaje();
	}

	public String getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public static List<ProductoDTO> construir(List<Producto> productos) {
		List<ProductoDTO> dtos = new ArrayList<ProductoDTO>();
		for (Producto producto : productos) {
			dtos.add(new ProductoDTO(producto));
		}
		return dtos;
	}

}
