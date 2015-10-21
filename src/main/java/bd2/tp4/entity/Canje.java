package bd2.tp4.entity;

import java.sql.Date;

public class Canje {
	private String id;
	private Producto producto;
	private Date fecha;

	public Canje() {
		super();
	}

	public Canje(Producto producto, Date fecha) {
		super();
		this.producto = producto;
		this.fecha = fecha;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setProducto(Producto producto){
		this.producto = producto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}

}
