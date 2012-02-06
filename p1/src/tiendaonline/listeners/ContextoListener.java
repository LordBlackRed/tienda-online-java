package tiendaonline.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextoListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
		arg0.getServletContext().setAttribute("emf", emfInstance);
		
	}
}
