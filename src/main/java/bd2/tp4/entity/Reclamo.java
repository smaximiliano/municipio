package bd2.tp4.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Reclamo {
	private String id;
	private Date fecha;
	private String descripcion;
	private String direccion;
	private Set<Evento> eventos;
	private Categoria categoria;

	private static final String MSG_ERROR_EVENTO_NO_ENCONTRADO = "Evento no encontrado, descripcion y fecha:";

	public Reclamo() {
		super();
	}

	public Reclamo(Date fecha, String descripcion, String direccion,
			Categoria categoria) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.eventos = new HashSet<Evento>();
		this.categoria = categoria;
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

	public Set<Evento> getEventos() {
		return eventos;
	}

	public Categoria getCategoria(){
		return categoria;
	}

	public void crearEvento(String descripcion, Date fecha){
		Evento evento = new Evento(descripcion, fecha);
		evento.setReclamo(this);
		eventos.add(evento);
	}

	public void addEvento(Evento e){
		e.setReclamo(this);
		eventos.add(e);
	}

	public Evento getEvento(String descripcion, Date fecha){
		Evento evento = null;
		for (Evento e : eventos)
			if (e.getDescripcion().equals(descripcion) && e.getFecha().equals(fecha))
				evento = e;
		if (evento == null)
			throw new RuntimeException(MSG_ERROR_EVENTO_NO_ENCONTRADO+" "+descripcion+" "+fecha.toString());
		return evento;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public void setEventos(Set<Evento> eventos){
		this.eventos = eventos;
	}

	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}
}
