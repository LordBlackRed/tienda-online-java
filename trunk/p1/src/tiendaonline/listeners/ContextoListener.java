package tiendaonline.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Factura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ContextoListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public static List<Producto> productosCabecera;
	public static Producto productoEspecial;
	public static List<String> sponsor;

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
