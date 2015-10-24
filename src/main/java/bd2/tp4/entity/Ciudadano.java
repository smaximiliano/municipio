package bd2.tp4.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import bd2.tp4.utility.CanjeUtil;

@Entity
public class Ciudadano {

	private static final int MULTIPLICADOR_BONUS = 2;
	private static final int MES_BONUS = 8;
	private static final String MSG_ERROR_RECLAMO_NO_ENCONTRADO = "Reclamo no encontrado... Fecha, descripcion y direccion:";
	private static final String MSG_ERROR_CANJE_NO_ENCONTRADO = "Canje no encontrado... Descripcion de producto y fecha:";
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Reclamo> reclamosRealizados;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Canje> canjes;
	private int puntajeTotal;

	public Ciudadano() {
		super();
	}

	public Ciudadano(String nombre, String apellido, String dni,
			String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntajeTotal = 0;
		reclamosRealizados = new HashSet<Reclamo>();
		canjes = new HashSet<Canje>();
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

	public int getPuntajeTotal() {
		return puntajeTotal;
	}

	public Set<Reclamo> getReclamosRealizados() {
		return reclamosRealizados;
	}

	public Set<Canje> getCanjes() {
		return canjes;
	}

	public void setCanjes(Set<Canje> canjes) {
		this.canjes = canjes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setReclamosRealizados(Set<Reclamo> reclamosRealizados) {
		this.reclamosRealizados = reclamosRealizados;
	}

	public void setPuntajeTotal(int puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public void crearReclamo(Date fecha, String descripcion,
			String direccion, Categoria categoria) {
		Reclamo reclamo = new Reclamo(fecha, descripcion, direccion,
				categoria);
		reclamosRealizados.add(reclamo);
		int puntajeReclamo = reclamo.getCategoria().getPuntaje();
		puntajeTotal += siEsElMesDeBonusDuplicarPuntos(fecha, puntajeReclamo);
	}

	@SuppressWarnings("deprecation")
	private int siEsElMesDeBonusDuplicarPuntos(Date fecha, int puntajeReclamo) {
		if (fecha.getMonth() == MES_BONUS) {
			return puntajeReclamo * MULTIPLICADOR_BONUS;
		} else
			return puntajeReclamo;
	}

	public void canjear(Producto producto) {
		if (puntajeTotal < producto.getPuntaje())
			throw new RuntimeException("El ciudadano " + nombre + " "
					+ apellido
					+ " no posee suficientes puntos para canjear el producto "
					+ producto.getDescripcion());

		java.util.Date utilDate = new java.util.Date();
		Date fecha = new Date(utilDate.getTime());
		CanjeUtil.setFechaUltimoCanje(fecha);
		puntajeTotal -= siEsElMesDeBonusNoDescontarPuntosAlCanjear(fecha,
				producto.getPuntaje());
		Canje canje = new Canje(producto, fecha);
		canjes.add(canje);
	}

	@SuppressWarnings("deprecation")
	private int siEsElMesDeBonusNoDescontarPuntosAlCanjear(Date fecha,
			int puntajeAReducir) {
		if (fecha.getMonth() == MES_BONUS) {
			return 0;
		} else
			return puntajeAReducir;
	}

	public Reclamo getReclamo(Date fecha, String descripcion, String direccion){
		Reclamo reclamo = null;
		for(Reclamo r : reclamosRealizados){
			if (r.getFecha().equals(fecha) && r.getDescripcion().equals(descripcion) && r.getDireccion().equals(direccion))
				reclamo = r;
		}
		if (reclamo == null)
			throw new RuntimeException(MSG_ERROR_RECLAMO_NO_ENCONTRADO+" "+fecha+", "+descripcion+", "+direccion);
		return reclamo;
	}

	public Canje getUltimoCanje(Producto producto){
		Canje canje = null;
		for (Canje c : canjes)
				if (c.getProducto().equals(producto) && c.getFecha().equals(CanjeUtil.getFechaUltimoCanje()))
					canje = c;
		if (canje == null)
			throw new RuntimeException(MSG_ERROR_CANJE_NO_ENCONTRADO+" "+producto.getDescripcion()+", "+CanjeUtil.getFechaUltimoCanje());
		return canje;
	}
}
