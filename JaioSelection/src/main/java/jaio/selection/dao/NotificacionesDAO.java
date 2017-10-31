package jaio.selection.dao;

import java.util.List;

import jaio.selection.entity2.Notificaciones;

public interface NotificacionesDAO {

	public abstract boolean crearNotificaciones(Notificaciones Notificaciones);
	public abstract boolean actualizaNotificaciones(Notificaciones Notificaciones);
	public abstract Notificaciones obtenerNotificaciones(Integer Id);
	public abstract boolean borrarNotificaciones(Notificaciones Notificaciones);
	public abstract List<Notificaciones> obtenerNotificacioness();
	
}
