package bd2.tp4.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bd2.tp4.entity.Categoria;

public class CategoriaDTO {
	private String id;
	private String nombre;
	private int puntaje;

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nombre = categoria.getNombre();
		this.puntaje = categoria.getPuntaje();
	}

	public static List<CategoriaDTO> construir(Collection<Categoria> categorias) {
		List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();
		for (Categoria categoria : categorias) {
			categoriasDTO.add(new CategoriaDTO(categoria));
		}
		return categoriasDTO;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

}
