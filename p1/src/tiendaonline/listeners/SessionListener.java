package tiendaonline.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession sesion = arg0.getSession();
		//Creamos la lista del carrito vac’a
		List<Producto> carrito = new ArrayList<Producto>();
		sesion.setAttribute(MisAtributos.carrito.toString(), carrito);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
