package tiendaonline.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import tiendaonline.clases.Producto;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ContextoListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public static List<Producto> productosCabecera;
	public static Producto productoEspecial;
	public static List<String> sponsor;

	@SuppressWarnings("rawtypes")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		arg0.getServletContext().setAttribute("usuarios", new HashMap());

		EntityManagerFactory emfInstance = Persistence
				.createEntityManagerFactory("transactions-optional");
		arg0.getServletContext().setAttribute("emf", emfInstance);

		productosCabecera = new ArrayList<Producto>();
		productosCabecera.add(new Producto());
		productosCabecera.add(new Producto());
		productosCabecera.add(new Producto());
		productosCabecera.add(new Producto());
		productosCabecera.add(new Producto());

		sponsor = new ArrayList<String>();
		sponsor.add(new String());
		sponsor.add(new String());
	}
}
