package bd2.tp4.dto;


import java.util.ArrayList;
import java.util.List;

import bd2.tp4.entity.Ciudadano;

public class CiudadanoDTO {

	private String id;
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private int puntajeTotal;

	public CiudadanoDTO(Ciudadano ciudadano) {
		super();
		id = ciudadano.getId();
		nombre = ciudadano.getNombre();
		apellido = ciudadano.getApellido();
		dni = ciudadano.getDni();
		email = ciudadano.getEmail();
		puntajeTotal = ciudadano.getPuntajeTotal();
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}
	public int getPuntajeTotal(){
		return puntajeTotal;
	}

	public static List<CiudadanoDTO> construir(List<Ciudadano> ciudadanos) {
		List<CiudadanoDTO> dtos = new ArrayList<CiudadanoDTO>();
		for (Ciudadano ciudadano : ciudadanos) {
			dtos.add(new CiudadanoDTO(ciudadano));
		}
		return dtos;
	}

}
