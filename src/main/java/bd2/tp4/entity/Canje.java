package bd2.tp4.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Canje {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne
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
