package jaio.selection.service;

import java.util.List;

import jaio.selection.entity.Notificaciones;

public interface NotificacionesService {

	public abstract boolean crearNotificaciones(Notificaciones Notificaciones);
	public abstract boolean actualizaNotificaciones(Notificaciones Notificaciones);
	public abstract Notificaciones obtenerNotificaciones(Integer Id);
	public abstract boolean borrarNotificaciones(Notificaciones Notificaciones);
	public abstract List<Notificaciones> obtenerNotificacioness();
	
}
