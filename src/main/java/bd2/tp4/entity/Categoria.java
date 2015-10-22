package bd2.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String nombre;
	private int puntaje;

	public Categoria() {
		super();
	}

	public Categoria(String nombre, int puntaje) {
		super();
		this.nombre = nombre;
		this.puntaje = puntaje;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

}
