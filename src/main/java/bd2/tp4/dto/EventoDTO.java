package bd2.tp4.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import bd2.tp4.entity.Evento;

public class EventoDTO {
	private String descripcion;
	private Date fecha;
	
	public EventoDTO(Evento evento) {
		super();
		descripcion = evento.getDescripcion();
		fecha = evento.getFecha();
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public Date getFecha() {
		return fecha;
	}

	public static List<EventoDTO> construir(List<Evento> all) {
		List<EventoDTO> eventosDTO = new ArrayList<EventoDTO>();
		EventoDTO eventoDTO;
		for (Evento evento : all) {
			eventoDTO = new EventoDTO(evento);
			eventosDTO.add(eventoDTO);
		}
		return eventosDTO;
	}
}
