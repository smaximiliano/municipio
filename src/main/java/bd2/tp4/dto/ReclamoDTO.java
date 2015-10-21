package bd2.tp4.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bd2.tp4.entity.Reclamo;

public class ReclamoDTO {
	private String id;
	private Date fecha;
	private String descripcion;
	private String direccion;

	public ReclamoDTO(Reclamo reclamo) {
		id = reclamo.getId();
		fecha = reclamo.getFecha();
		descripcion = reclamo.getDescripcion();
		direccion = reclamo.getDireccion();
	}

	public String getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public static List<ReclamoDTO> construir(List<Reclamo> reclamos) {
		List<ReclamoDTO> reclamosDTO = new ArrayList<ReclamoDTO>();
		
		for (Reclamo reclamo : reclamos) {
			reclamosDTO.add(new ReclamoDTO(reclamo));
		}
		return reclamosDTO;
	}
	
	
}
