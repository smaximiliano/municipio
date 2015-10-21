package bd2.tp4.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bd2.tp4.entity.Canje;
import bd2.tp4.entity.Producto;

public class CanjeDTO {

	private String id;
	private Producto producto;
	private Date fecha;

	public CanjeDTO(Canje canje) {
		this.id = canje.getId();
		this.producto = canje.getProducto();
		this.fecha = canje.getFecha();
	}

	public String getId() {
		return id;
	}

	public Producto getProducto() {
		return producto;
	}

	public Date getFecha() {
		return fecha;
	}

	public static List<CanjeDTO> construir(List<Canje> canjes) {
		List<CanjeDTO> dtos = new ArrayList<CanjeDTO>();
		for (Canje canje : canjes) {
			dtos.add(new CanjeDTO(canje));
		}
		return dtos;
	}

}
