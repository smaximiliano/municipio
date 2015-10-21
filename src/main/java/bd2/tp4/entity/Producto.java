package bd2.tp4.entity;

public class Producto {
	private String id;
	private String descripcion;
	private int puntaje;

	public Producto() {
		super();
	}

	public Producto(String descripcion, int puntaje) {
		super();
		this.descripcion = descripcion;
		this.puntaje = puntaje;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

}
