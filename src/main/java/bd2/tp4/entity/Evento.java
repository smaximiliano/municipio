package bd2.tp4.entity;

import java.sql.Date;

public class Evento {
	private String id;
	private String descripcion;
	private Date fecha;
	private Reclamo reclamo;

	public Evento() {
		super();
	}

	public Evento(String descripcion, Date fecha) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public Reclamo getReclamo(){
		return reclamo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

}
