package bd2.tp4.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Municipio {
	private static final String ID_DEL_UNICO_MUNICIPIO = "1";
	@Id
	private String id;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Ciudadano> ciudadanos;
	
	public Municipio(){
		id = ID_DEL_UNICO_MUNICIPIO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Ciudadano> getCiudadanos() {
		return ciudadanos;
	}

	public void setCiudadanos(Set<Ciudadano> ciudadanos) {
		this.ciudadanos = ciudadanos;
	}

	public void removeCiudadano(Ciudadano ciudadano) {
		ciudadanos.remove(ciudadano);
	}

	public void addCiudadano(Ciudadano ciudadano) {
		ciudadanos.add(ciudadano);
	}

}
