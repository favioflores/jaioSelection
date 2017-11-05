package jaio.selection.service.impl;

import java.io.Serializable;
import java.util.List;

import jaio.selection.dao.impl.NotificacionesDAOImpl;
import jaio.selection.entity.Notificaciones;
import jaio.selection.service.NotificacionesService;

public class NotificacionesServiceImpl implements NotificacionesService, Serializable {

	private NotificacionesDAOImpl NotificacionesDAO;

	public void setNotificacionesDAO(NotificacionesDAOImpl NotificacionesDAO) {
		this.NotificacionesDAO = NotificacionesDAO;
	}

	public boolean crearNotificaciones(Notificaciones Notificaciones) {
		return NotificacionesDAO.crearNotificaciones(Notificaciones);
	}
	public boolean actualizaNotificaciones(Notificaciones Notificaciones) {
		return NotificacionesDAO.actualizaNotificaciones(Notificaciones);
	}
	public Notificaciones obtenerNotificaciones(Integer Id) {
		return NotificacionesDAO.obtenerNotificaciones(Id);
	}
	public boolean borrarNotificaciones(Notificaciones Notificaciones) {
		return NotificacionesDAO.borrarNotificaciones(Notificaciones);
	}
	public List<Notificaciones> obtenerNotificacioness() {
		return NotificacionesDAO.obtenerNotificacioness();
	}
	
}